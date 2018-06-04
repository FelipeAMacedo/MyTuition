package com.example.felipemacedo.mytuition.dto.usuarioConquista;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioConquistaUsuarioDTO implements Serializable {

	@SerializedName("email")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
