package com.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UsuarioConteudo implements Serializable {

    private static final long serialVersionUID = -134072727402007115L;

    private UsuarioConteudoId id;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Usuario usuario;
    private Conteudo conteudo;

    public UsuarioConteudoId getId() {
        return id;
    }

    public void setId(UsuarioConteudoId id) {
        this.id = id;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

}
