package br.com.fiap.enjoy.domain;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_VISITA")
public class Visita {
	
	@Id
	@SequenceGenerator(name="visita",sequenceName="SEQ_VISITA",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="visita")
	@Column(name = "cd_visita")
	private int codVisita;
	
	@Column(name = "dt_visita", nullable = false)
	private Calendar dataVisita;
	
	@Column(name = "vl_ticket", precision = 5, scale = 2, nullable = false)
	private float valorTicket;
	
	@JoinColumn(name = "n_telefone")
	@ManyToOne
	private Cliente cliente;
	
	@JoinColumn(name = "cd_estabelecimento")
	@ManyToOne
	private Estabelecimento estabelecimento;
	
	@OneToMany(mappedBy = "visita")
	private Collection<VisitaBebida> bebidas;
	
	public Visita() {
	}

	public int getCodVisita() {
		return codVisita;
	}

	public void setCodVisita(int codVisita) {
		this.codVisita = codVisita;
	}

	public Calendar getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Calendar dataVisita) {
		this.dataVisita = dataVisita;
	}

	public float getValorTicket() {
		return valorTicket;
	}

	public void setValorTicket(float valorTicket) {
		this.valorTicket = valorTicket;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Collection<VisitaBebida> getBebidas() {
		return bebidas;
	}

	public void setBebidas(Collection<VisitaBebida> bebidas) {
		this.bebidas = bebidas;
	}
	
}
