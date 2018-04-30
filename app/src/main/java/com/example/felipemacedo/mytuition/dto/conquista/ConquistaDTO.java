package com.example.felipemacedo.mytuition.dto.conquista;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConquistaDTO implements Serializable {

	private static final long serialVersionUID = 7405630745140496442L;

	@SerializedName("nome")
	private String nome;

	@SerializedName("descricao")
	private String descricao;

	@SerializedName("imagem")
	private String imagem;

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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
