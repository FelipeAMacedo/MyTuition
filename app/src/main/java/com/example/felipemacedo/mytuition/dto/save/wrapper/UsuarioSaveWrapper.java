package com.example.felipemacedo.mytuition.dto.save.wrapper;

import com.example.felipemacedo.mytuition.dto.UsuarioDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioSaveWrapper implements Serializable {

	private static final long serialVersionUID = -8548942540740875853L;

	@SerializedName("usuario")
	private UsuarioDTO usuarioDTO;

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

}
