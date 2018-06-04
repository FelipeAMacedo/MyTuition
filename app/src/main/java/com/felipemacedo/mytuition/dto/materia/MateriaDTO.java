package com.felipemacedo.mytuition.dto.materia;

import com.felipemacedo.mytuition.enums.Atributo;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

public class MateriaDTO implements Serializable {

	private static final long serialVersionUID = 4581686743290460035L;

	@SerializedName("nome")
	private String nome;

	@SerializedName("pontos")
	private Integer pontos;

	@SerializedName("disciplina")
	private DisciplinaDTO disciplina;

	@SerializedName("conteudo")
	private Set<ConteudoDTO> conteudo;

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

	public DisciplinaDTO getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(DisciplinaDTO disciplina) {
		this.disciplina = disciplina;
	}

	public Set<ConteudoDTO> getConteudo() {
		return conteudo;
	}

	public void setConteudo(Set<ConteudoDTO> conteudo) {
		this.conteudo = conteudo;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

}
