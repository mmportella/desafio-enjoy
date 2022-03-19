package br.com.fiap.enjoy.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_CLIENTE")
public class Cliente {

	@Id
	@Column(name = "n_telefone")
	private int numeroTelefone;
	
	@Column(name = "nm_cliente", length = 30, nullable = false)
	private String nomeCliente;
	
	@Column(name = "ds_email", length = 20, nullable = true)
	private String email;
	
	@Column(name = "ds_genero", length = 10, nullable = true)
	private String genero;
	
	@Column(name = "dt_nasc", nullable = true)
	private Calendar dataNasc;
	
	@Column(name = "nr_cpf", nullable = true)
	private int cpf;
	
	@Column(name = "ds_endereco", length = 60, nullable = true)
	private String endereco;
	
	public Cliente() {
	}

	public int getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(int numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Calendar getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Calendar dataNasc) {
		this.dataNasc = dataNasc;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
