package br.com.fiap.enjoy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_VISITA_BEBIDA")
public class VisitaBebida {
	
	@Id
	@SequenceGenerator(name="visitabebida",sequenceName="SEQ__VISITA_BEBIDA",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="visitabebida")
	@Column(name = "cd_visitabebida")
	private int codVisitaBebida;
	
	@Column(name = "qt_bebida", nullable = false)
	private int qtdBebida;
	
	@JoinColumn(name = "cd_visita")
	@ManyToOne
	private Visita visita;
	
	@JoinColumn(name = "cd_bebida")
	@ManyToOne
	private Bebida bebida;
	
	public VisitaBebida() {
	}

	public int getCodVisitaBebida() {
		return codVisitaBebida;
	}

	public void setCodVisitaBebida(int codVisitaBebida) {
		this.codVisitaBebida = codVisitaBebida;
	}

	public int getQtdBebida() {
		return qtdBebida;
	}

	public void setQtdBebida(int qtdBebida) {
		this.qtdBebida = qtdBebida;
	}

	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}
	
	

}
