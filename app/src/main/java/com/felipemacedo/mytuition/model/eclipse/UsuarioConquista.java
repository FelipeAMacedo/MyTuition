package com.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;
import java.time.LocalDate;

public class UsuarioConquista implements Serializable {

    private static final long serialVersionUID = -6782130291264790383L;

    private UsuarioConquistaId id;
    private LocalDate dataConquista;
    private Usuario usuario;
    private Conquista conquista;

    public UsuarioConquistaId getId() {
        return id;
    }

    public void setId(UsuarioConquistaId id) {
        this.id = id;
    }

    public LocalDate getDataConquista() {
        return dataConquista;
    }

    public void setDataConquista(LocalDate dataConquista) {
        this.dataConquista = dataConquista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conquista getConquista() {
        return conquista;
    }

    public void setConquista(Conquista conquista) {
        this.conquista = conquista;
    }

}
