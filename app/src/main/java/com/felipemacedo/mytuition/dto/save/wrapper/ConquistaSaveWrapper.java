package com.felipemacedo.mytuition.dto.save.wrapper;

import com.felipemacedo.mytuition.dto.conquista.ConquistaDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConquistaSaveWrapper implements Serializable {

	private static final long serialVersionUID = -4920205047270404229L;

	@SerializedName("conquista")
	private ConquistaDTO conquistaDTO;

	public ConquistaDTO getConquistaDTO() {
		return conquistaDTO;
	}

	public void setConquistaDTO(ConquistaDTO conquistaDTO) {
		this.conquistaDTO = conquistaDTO;
	}

}
