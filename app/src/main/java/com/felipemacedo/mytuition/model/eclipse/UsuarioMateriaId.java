package com.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;


public class UsuarioMateriaId implements Serializable {

	private String usuarioId;
	private Long materiaId;

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getMateriaId() {
		return materiaId;
	}

	public void setMateriaId(Long materiaId) {
		this.materiaId = materiaId;
	}

}
