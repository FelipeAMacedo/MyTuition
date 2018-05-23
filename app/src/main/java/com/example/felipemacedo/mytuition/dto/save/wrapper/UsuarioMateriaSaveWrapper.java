package com.example.felipemacedo.mytuition.dto.save.wrapper;

import com.example.felipemacedo.mytuition.dto.LoginDTO;
import com.example.felipemacedo.mytuition.dto.usuarioMateria.UsuarioMateriaDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioMateriaSaveWrapper implements Serializable {

	@SerializedName("usuarioMateria")
	private UsuarioMateriaDTO usuarioMateriaDTO;

	public UsuarioMateriaDTO getUsuarioMateriaDTO() {
		return usuarioMateriaDTO;
	}

	public void setUsuarioMateriaDTO(UsuarioMateriaDTO usuarioMateriaDTO) {
		this.usuarioMateriaDTO = usuarioMateriaDTO;
	}

}
