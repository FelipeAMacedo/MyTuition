package com.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Conteudo implements Serializable {

    private static final long serialVersionUID = -6216301285326219806L;

    private Long id;
    private String texto;
    private LocalTime duracao;
    private Integer ordem;
    private Materia materia;
    private Ataque ataque;

    private Set<Alternativa> alternativas = new HashSet<>(0);

    private Set<UsuarioConteudo> usuarioConteudo = new HashSet<>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Ataque getAtaque() {
        return ataque;
    }

    public void setAtaque(Ataque ataque) {
        this.ataque = ataque;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Set<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(Set<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public Set<UsuarioConteudo> getUsuarioConteudo() {
        return usuarioConteudo;
    }

    public void setUsuarioConteudo(Set<UsuarioConteudo> usuarioConteudo) {
        this.usuarioConteudo = usuarioConteudo;
    }

}
