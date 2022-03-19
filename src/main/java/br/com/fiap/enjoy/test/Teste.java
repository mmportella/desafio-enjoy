package br.com.fiap.enjoy.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.fiap.enjoy.domain.Bebida;
import br.com.fiap.enjoy.domain.Visita;
import br.com.fiap.enjoy.domain.VisitaBebida;

public class Teste {
	
	public static void main(String[] args) {
		
		int telefoneUsuario = 119123456;
		
		String ultimaVisita = ultimaVisita(telefoneUsuario);
		System.out.println(ultimaVisita);
		
		Preferida preferida = estiloBebida(telefoneUsuario);
		System.out.println("Sua bebida preferida é a " + preferida.getBebida() +
							", uma bebida do tipo " + preferida.getTipo() +
							", e você a bebeu " + preferida.getQuantidade() +
							" vezes.");
		
		
	}
	
	
	
	// Métodos
	
	// Data da última visita 
	private static String ultimaVisita(int telefone) {
		
		EntityManager em = Persistence.createEntityManagerFactory("desafio-enjoy").createEntityManager();
		
		Query q = em.createQuery("from Visita where n_telefone = :telefone order by dt_visita DESC");
		q.setParameter("telefone", telefone);
		q.setMaxResults(1);
		List<Visita> results = q.getResultList();
		em.close();
		
		return results.get(0).getDataVisita().toString();
	}

	// frequência (quantidade de visitas pelo total de dias)
	//private static String frequencia(int telefone) {
		
		
	//}
	
	// ticket médio - 
	//private static String ticketMedio() {
		
	//}
	
	
	
	// Bebida preferida, estilo e quantidade
	
	private static class Preferida {
	    private String bebida;
	    private String quantidade;
	    private String tipo;
	    
	    public Preferida(String bebida, String quantidade, String tipo) {
	    	this.bebida = bebida;
	    	this.quantidade = quantidade;
	    	this.tipo = tipo;
	    }
	    
	    public String getBebida() {
			return bebida;
		}
	    
	    public String getTipo() {
			return tipo;
		}
	    
	    public String getQuantidade() {
	    	return quantidade;
	    }
	}
	
	private static Preferida estiloBebida(int telefone) {
	
		String tel = String.valueOf(telefone); 
		EntityManager em = Persistence.createEntityManagerFactory("desafio-enjoy").createEntityManager();
		
		Query q = em.createQuery("from Visita where n_telefone = :telefone order by dt_visita DESC");
		q.setParameter("telefone", tel);
		q.setMaxResults(10);
		List<Visita> results = q.getResultList();
		
		List<Integer> list = new ArrayList<Integer>();
		
		for (Visita v : results) {
			Collection<VisitaBebida> vb = v.getBebidas();
			for (VisitaBebida vb2 : vb) {
				Bebida b = vb2.getBebida();
				list.add(b.getCodBebida());
			}
		}
		
		var bebidaPreferida = list.stream()
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		.entrySet().stream()
				   .sorted(Map.Entry.<Integer, Long>comparingByValue()
				   .reversed())
				   .limit(1)
				   .findFirst()
				   .get()
				   .getKey();
		
		var qtdBebida = list.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
						   .sorted(Map.Entry.<Integer, Long>comparingByValue()
						   .reversed())
						   .limit(1)
						   .findFirst()
						   .get()
						   .getValue();
		
		Query q2 = em.createQuery("from Bebida where cd_bebida = :cdbebida");
		q2.setParameter("cdbebida", bebidaPreferida);
		q2.setMaxResults(1);
		List<Bebida> resultados = q2.getResultList();
		
		String nomeBebida = resultados.get(0).getNomeBebida();
		String tipoBebida = resultados.get(0).getTipoBebida();
		String quantidade = qtdBebida.toString();
		
		Preferida preferida = new Preferida(nomeBebida, quantidade, tipoBebida);
		em.close();
		
		return preferida;
		
	}
	
}
