package com.example.felipemacedo.mytuition.model.eclipse;

import com.felipemacedo.mytuition.enums.Atributo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Materia implements Serializable {

    private static final long serialVersionUID = 6005322491077995346L;

    private Long id;

    private String nome;
    private Integer pontos;
    private Atributo atributo;
    private Disciplina disciplina;
    private Set<Conteudo> conteudo = new HashSet<>(0);
    private Set<UsuarioMateria> usuarioMateria = new HashSet<>(0);

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

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Set<Conteudo> getConteudo() {
        return conteudo;
    }

    public void setConteudo(Set<Conteudo> conteudo) {
        this.conteudo = conteudo;
    }

    public Set<UsuarioMateria> getUsuarioMateria() {
        return usuarioMateria;
    }

    public void setUsuarioMateria(Set<UsuarioMateria> usuarioMateria) {
        this.usuarioMateria = usuarioMateria;
    }
}
