package com.felipemacedo.mytuition.dto.save.wrapper;

import com.felipemacedo.mytuition.dto.conquista.ConquistaUsuarioDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConquistaUsuarioSaveWrapper implements Serializable {

	private static final long serialVersionUID = -2398192563883166969L;

	@SerializedName("conquista")
	private ConquistaUsuarioDTO conquistaUsuarioDTO;

	public ConquistaUsuarioDTO getConquistaUsuarioDTO() {
		return conquistaUsuarioDTO;
	}

	public void setConquistaUsuarioDTO(ConquistaUsuarioDTO conquistaUsuarioDTO) {
		this.conquistaUsuarioDTO = conquistaUsuarioDTO;
	}

}
