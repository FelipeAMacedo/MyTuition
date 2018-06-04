package com.felipemacedo.mytuition.dto.heroi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AtualizacaoExperienciaDTO implements Serializable {

	private static final long serialVersionUID = 5139843617220555968L;

	@SerializedName("id")
	private Long id;

	@SerializedName("pontosAdicionais")
	private Integer pontosAdicionais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPontosAdicionais() {
		return pontosAdicionais;
	}

	public void setPontosAdicionais(Integer pontosAdicionais) {
		this.pontosAdicionais = pontosAdicionais;
	}

}
