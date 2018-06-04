package com.felipemacedo.mytuition.dto.disciplina;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DisciplinaDTO implements Serializable {

	private static final long serialVersionUID = -7789050597369157033L;

	@SerializedName("nome")
	private String nome;

	@SerializedName("descricao")
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
