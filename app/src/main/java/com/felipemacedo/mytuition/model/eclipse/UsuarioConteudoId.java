package com.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;

public class UsuarioConteudoId implements Serializable {

    private static final long serialVersionUID = -5792908029997332776L;
    private Long usuarioId;
    private Long conteudoId;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getConteudoId() {
        return conteudoId;
    }

    public void setConteudoId(Long conteudoId) {
        this.conteudoId = conteudoId;
    }

}
