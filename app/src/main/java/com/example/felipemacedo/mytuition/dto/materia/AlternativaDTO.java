package com.example.felipemacedo.mytuition.dto.materia;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlternativaDTO implements Serializable {

	private static final long serialVersionUID = -5427400437011577513L;

	@SerializedName("ordem")
	private Integer ordem;

	@SerializedName("texto")
	private String texto;

	@SerializedName("certo")
	private Boolean certo;

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Boolean getCerto() {
		return certo;
	}

	public void setCerto(Boolean certo) {
		this.certo = certo;
	}

}
