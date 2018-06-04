package com.felipemacedo.mytuition.model.eclipse;

import com.felipemacedo.mytuition.model.eclipse.Aluno;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Curso implements Serializable {

	private static final long serialVersionUID = 7079924842473811414L;

	private Long id;
	private String nome;
	private String descricao;

	private Set<Aluno> alunos = new HashSet<>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}
