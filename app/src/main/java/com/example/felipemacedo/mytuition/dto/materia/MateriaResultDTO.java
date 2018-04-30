package com.example.felipemacedo.mytuition.dto.materia;

import com.example.felipemacedo.mytuition.enums.Atributo;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MateriaResultDTO implements Serializable {

	private static final long serialVersionUID = -8729032093983945789L;

	@SerializedName("nome")
	private String nome;

	@SerializedName("pontos")
	private Integer pontos;

	@SerializedName("atributo")
	private Atributo atributo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

}
