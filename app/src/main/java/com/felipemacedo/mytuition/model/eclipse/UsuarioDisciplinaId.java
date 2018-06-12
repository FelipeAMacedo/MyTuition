package com.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;

public class UsuarioDisciplinaId implements Serializable {

    private static final long serialVersionUID = 5194698684021449931L;

    private String usuarioId;
    private Long disciplinaId;

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

}
