package br.com.fiap.enjoy.test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
		System.out.println("A data da última visita do cliente foi em " + ultimaVisita);
		
		Preferida preferida = estiloBebida(telefoneUsuario);
		System.out.println("A bebida preferida de " + telefoneUsuario + " é a " + preferida.getBebida() +
							", uma bebida do tipo " + preferida.getTipo() +
							", e foi consumida " + preferida.getQuantidade() +
							" vezes.");
		
		
		String frequencia = frequencia(telefoneUsuario);
		System.out.println(frequencia);
		
		int cdEstabelecimento = 1;
		
		String ticketMedio = ticketMedio(cdEstabelecimento);
		System.out.println("O ticket médio do estabelecimento " + cdEstabelecimento + " foi de R$" + ticketMedio);
		
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(results.get(0).getDataVisita().getTime());
		return date;
		
	}

	
	
	// frequência (quantidade de visitas pelo total de dias)
	private static String frequencia(int telefone) {
		
		EntityManager em = Persistence.createEntityManagerFactory("desafio-enjoy").createEntityManager();
		
		Query q = em.createQuery("from Visita where n_telefone = :telefone");
		q.setParameter("telefone", telefone);
		q.setMaxResults(100);
		List<Visita> lista = q.getResultList();

		int i = lista.size();
		
		LocalDate hoje = LocalDate.now();
		LocalDate primeiraVisita = LocalDate.ofInstant(lista.get(0).getDataVisita().toInstant(), ZoneId.systemDefault());
		
		long p = ChronoUnit.DAYS.between(primeiraVisita, hoje);
		
		String str = String.format("A frequência de visitas do cliente foi de " +
				"%d vezes nos últimos %d dias", i, p);
		
		return str;
		
	}
	
	
	
	// ticket médio - 
	private static String ticketMedio(int cdEstabelecimento) {
		
		EntityManager em = Persistence.createEntityManagerFactory("desafio-enjoy").createEntityManager();
		
		Query q = em.createQuery("from Visita where cd_estabelecimento = :cd");
		q.setParameter("cd", cdEstabelecimento);
		List<Visita> lista = q.getResultList();
		
		int visitas = lista.size();
		float ticket = 0.0f;
		
		for (Visita v : lista) {
			ticket = ticket + v.getValorTicket();
		}
		
		float ticketMedio = ticket / visitas;
		
		String str = Float.toString(ticketMedio);
		
		return str;
		
	}
	
	
	
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
	 
		EntityManager em = Persistence.createEntityManagerFactory("desafio-enjoy").createEntityManager();
		
		Query q = em.createQuery("from Visita where n_telefone = :telefone order by dt_visita DESC");
		q.setParameter("telefone", telefone);
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
