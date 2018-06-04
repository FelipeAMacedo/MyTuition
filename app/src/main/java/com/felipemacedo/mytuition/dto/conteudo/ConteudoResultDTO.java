package com.example.felipemacedo.mytuition.dto.conteudo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

public class ConteudoResultDTO implements Serializable {

	private static final long serialVersionUID = -6888810032548854299L;

	@SerializedName("id")
	private Long id;

	@SerializedName("ordem")
	private Integer ordem;

	@SerializedName("texto")
	private String texto;

	@SerializedName("duracao")
	private LocalTime duracao;

	@SerializedName("alternativas")
	private Set<AlternativasResultDTO> alternativas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalTime getDuracao() {
		return duracao;
	}

	public void setDuracao(LocalTime duracao) {
		this.duracao = duracao;
	}

	public Set<AlternativasResultDTO> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(Set<AlternativasResultDTO> alternativas) {
		this.alternativas = alternativas;
	}

}
