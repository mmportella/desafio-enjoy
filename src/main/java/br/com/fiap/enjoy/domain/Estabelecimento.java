package br.com.fiap.enjoy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_ESTABELECIMENTO")
public class Estabelecimento {

	@Id
	@SequenceGenerator(name="estabelecimento",sequenceName="SEQ_ESTABELECIMENTO",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="estabelecimento")
	@Column(name = "cd_estabelecimento")
	private int codEstabelecimento;
	
	@Column(name = "nm_estabelecimento", length = 30, nullable = false)
	private String nomeEstabelecimento;
	
	public Estabelecimento() {
	}

	public int getCodEstabelecimento() {
		return codEstabelecimento;
	}

	public void setCodEstabelecimento(int codEstabelecimento) {
		this.codEstabelecimento = codEstabelecimento;
	}

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}
	
}
