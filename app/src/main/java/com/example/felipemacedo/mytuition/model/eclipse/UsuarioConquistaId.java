package com.example.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;

public class UsuarioConquistaId implements Serializable {

    private static final long serialVersionUID = -5905347524538446377L;

    private String usuarioId;
    private Long conquistaId;

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getConquistaId() {
        return conquistaId;
    }

    public void setConquistaId(Long conquistaId) {
        this.conquistaId = conquistaId;
    }

}
