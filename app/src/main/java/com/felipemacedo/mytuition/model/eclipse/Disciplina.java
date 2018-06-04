package com.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Disciplina implements Serializable {

    private static final long serialVersionUID = -5416749887977124159L;

    private Long id;
    private String nome;
    private String descricao;

    private Set<Materia> materias = new HashSet<>(0);
    private Set<UsuarioDisciplina> usuarioDisciplina = new HashSet<>(0);

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

    public Set<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(Set<Materia> materias) {
        this.materias = materias;
    }

    public Set<UsuarioDisciplina> getUsuarioDisciplina() {
        return usuarioDisciplina;
    }

    public void setUsuarioDisciplina(Set<UsuarioDisciplina> usuarioDisciplina) {
        this.usuarioDisciplina = usuarioDisciplina;
    }
}
