package com.felipemacedo.mytuition.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HeroiDTO implements Serializable {

	private static final long serialVersionUID = 8121810391676220L;

	@SerializedName("nome")
	private String nome;

	@SerializedName("imagem")
	private String imagem;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
