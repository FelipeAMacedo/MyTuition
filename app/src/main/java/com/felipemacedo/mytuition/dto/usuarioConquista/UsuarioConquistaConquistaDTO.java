package com.felipemacedo.mytuition.dto.usuarioConquista;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioConquistaConquistaDTO implements Serializable {

	@SerializedName("id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
