package com.example.felipemacedo.mytuition.dto.save.wrapper;

import com.example.felipemacedo.mytuition.dto.heroi.AtualizacaoExperienciaDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AtualizacaoExperienciaWrapper implements Serializable {

	private static final long serialVersionUID = 29172965326690849L;

	@SerializedName("heroi")
	private AtualizacaoExperienciaDTO atualizacaoExperienciaDTO;

	public AtualizacaoExperienciaDTO getAtualizacaoExperienciaDTO() {
		return atualizacaoExperienciaDTO;
	}

	public void setAtualizacaoExperienciaDTO(AtualizacaoExperienciaDTO atualizacaoExperienciaDTO) {
		this.atualizacaoExperienciaDTO = atualizacaoExperienciaDTO;
	}

}
