package com.felipemacedo.mytuition.dto.conquista;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class ConquistaUsuarioDTO implements Serializable {

	private static final long serialVersionUID = 7405630745140496442L;

	@SerializedName("idUsuario")
	private String idUsuario;

	@SerializedName("idConquista")
	private Long idConquista;

	@SerializedName("data")
	private LocalDate data;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdConquista() {
		return idConquista;
	}

	public void setIdConquista(Long idConquista) {
		this.idConquista = idConquista;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
