package com.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;

public class UsuarioDisciplinaId implements Serializable {

    private static final long serialVersionUID = 5194698684021449931L;

    private Long usuarioId;
    private Long conquistaId;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getConquistaId() {
        return conquistaId;
    }

    public void setConquistaId(Long conquistaId) {
        this.conquistaId = conquistaId;
    }

}
