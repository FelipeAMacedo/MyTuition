package com.felipemacedo.mytuition.dto.save.wrapper;

import com.felipemacedo.mytuition.dto.usuarioDisciplina.UsuarioDisciplinaDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioDisciplinaSaveWrapper implements Serializable {

	@SerializedName("usuarioDisciplina")
	private UsuarioDisciplinaDTO usuarioDisciplinaDTO;

	public UsuarioDisciplinaDTO getUsuarioDisciplinaDTO() {
		return usuarioDisciplinaDTO;
	}

	public void setUsuarioDisciplinaDTO(UsuarioDisciplinaDTO usuarioDisciplinaDTO) {
		this.usuarioDisciplinaDTO = usuarioDisciplinaDTO;
	}

}
