package com.example.felipemacedo.mytuition.dto.materia;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

public class ConteudoDTO implements Serializable {

	private static final long serialVersionUID = -778217820364161067L;

	@SerializedName("ordem")
	private Integer ordem;

	@SerializedName("texto")
	private String texto;

	@SerializedName("duracao")
	private LocalTime duracao;

	@SerializedName("alternativas")
	private Set<AlternativaDTO> alternativas;

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

	public Set<AlternativaDTO> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(Set<AlternativaDTO> alternativas) {
		this.alternativas = alternativas;
	}

}
