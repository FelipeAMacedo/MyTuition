package com.example.felipemacedo.mytuition.dto.save.wrapper;

import com.example.felipemacedo.mytuition.dto.conquista.ConquistaBuscaDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ConquistaBuscaWrapper implements Serializable {

	@SerializedName("conquistas")
	private List<ConquistaBuscaDTO> conquistaBuscaDTO;

	public List<ConquistaBuscaDTO> getConquistaBuscaDTO() {
		return conquistaBuscaDTO;
	}

	public void setConquistaBuscaDTO(List<ConquistaBuscaDTO> conquistaBuscaDTO) {
		this.conquistaBuscaDTO = conquistaBuscaDTO;
	}

}
