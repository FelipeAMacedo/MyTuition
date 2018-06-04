package com.felipemacedo.mytuition.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginDTO implements Serializable {

	private static final long serialVersionUID = 5598598162297350726L;

	@SerializedName("email")
	private String email;

	@SerializedName("senha")
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
