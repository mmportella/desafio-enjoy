package br.com.fiap.enjoy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_BEBIDA")
public class Bebida {

	@Id
	@SequenceGenerator(name="bebida",sequenceName="SEQ_BEBIDA",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="bebida")
	@Column(name = "cd_bebida")
	private int codBebida;
	
	@Column(name = "nm_bebida", length = 30, nullable = false)
	private String nomeBebida;
	
	@Column(name = "vl_bebida", precision = 5, scale = 2, nullable = false)
	private float valorBebida;
	
	@Column(name = "ds_tipo", length = 30, nullable = false)
	private String tipoBebida;
	
	
	public Bebida() {
	}


	public int getCodBebida() {
		return codBebida;
	}


	public void setCodBebida(int codBebida) {
		this.codBebida = codBebida;
	}


	public String getNomeBebida() {
		return nomeBebida;
	}


	public void setNomeBebida(String nomeBebida) {
		this.nomeBebida = nomeBebida;
	}


	public float getValorBebida() {
		return valorBebida;
	}


	public void setValorBebida(float valorBebida) {
		this.valorBebida = valorBebida;
	}


	public String getTipoBebida() {
		return tipoBebida;
	}


	public void setTipoBebida(String tipoBebida) {
		this.tipoBebida = tipoBebida;
	}
	
}
