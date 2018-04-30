package com.example.felipemacedo.mytuition.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class AlunoDTO implements Serializable {

	private static final long serialVersionUID = 5598598162297350726L;

	@SerializedName("nome")
	private String nome;

	@SerializedName("cpf")
	private String cpf;

	@SerializedName("ra")
	private String ra;

	@SerializedName("sexo")
	private Boolean sexo;

	@SerializedName("dataNascimento")
	private LocalDate dataNascimento;

	@SerializedName("dataEntrada")
	private LocalDate dataEntrada;
	
	@SerializedName("curso")
	private Long curso;
	
	@SerializedName("trabalhaArea")
	private Boolean trabalhaArea;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public Boolean getSexo() {
		return sexo;
	}

	public void setSexo(Boolean sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Boolean getTrabalhaArea() {
		return trabalhaArea;
	}

	public void setTrabalhaArea(Boolean trabalhaArea) {
		this.trabalhaArea = trabalhaArea;
	}

}
