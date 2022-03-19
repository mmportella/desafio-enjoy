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
		
		String query1 = ultimaVisita(telefoneUsuario);
		System.out.println(query1);
		
		estiloBebida(telefoneUsuario);
		
		
	}
	
	
	
	// Métodos
	
	// Data da última visita 
	private static String ultimaVisita(int telefone) {
		
		String tel = String.valueOf(telefone); 
		EntityManager em = Persistence.createEntityManagerFactory("desafio-enjoy").createEntityManager();
		
		Query q = em.createQuery("from Visita where n_telefone = :telefone order by dt_visita DESC");
		q.setParameter("telefone", tel);
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
	
	// bebida e estilo
	private static void estiloBebida(int telefone) {
	
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
		
		System.out.println("A sua bebida preferida é " + bebidaPreferida + ", e você a consumiu " + qtdBebida + " vezes.");
		
		
	}
	
}
