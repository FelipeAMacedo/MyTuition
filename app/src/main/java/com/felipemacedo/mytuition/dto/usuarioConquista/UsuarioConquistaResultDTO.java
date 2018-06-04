package com.example.felipemacedo.mytuition.dto.usuarioConquista;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UsuarioConquistaResultDTO implements Serializable {

	@SerializedName("conquista")
	private UsuarioConquistaConquistaDTO conquista;

	@SerializedName("usuario")
	private UsuarioConquistaUsuarioDTO usuario;

	@SerializedName("inicio")
	private LocalDateTime inicio;

	@SerializedName("conclusao")
	private LocalDateTime conclusao;

	public UsuarioConquistaConquistaDTO getConquista() {
		return conquista;
	}

	public void setConquista(UsuarioConquistaConquistaDTO conquista) {
		this.conquista = conquista;
	}

	public UsuarioConquistaUsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioConquistaUsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getConclusao() {
		return conclusao;
	}

	public void setConclusao(LocalDateTime conclusao) {
		this.conclusao = conclusao;
	}

}
