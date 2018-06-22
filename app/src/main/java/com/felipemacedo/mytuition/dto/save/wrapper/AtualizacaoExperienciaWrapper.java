package com.felipemacedo.mytuition.dto.save.wrapper;

import com.felipemacedo.mytuition.dto.heroi.AtualizacaoExperienciaDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AtualizacaoExperienciaWrapper implements Serializable {

	@SerializedName("heroi")
	private AtualizacaoExperienciaDTO atualizacaoExperienciaDTO;

	public AtualizacaoExperienciaDTO getAtualizacaoExperienciaDTO() {
		return atualizacaoExperienciaDTO;
	}

	public void setAtualizacaoExperienciaDTO(AtualizacaoExperienciaDTO atualizacaoExperienciaDTO) {
		this.atualizacaoExperienciaDTO = atualizacaoExperienciaDTO;
	}

}
