package com.felipemacedo.mytuition.dto.save.wrapper;

import com.felipemacedo.mytuition.dto.materia.MateriaDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MateriaSaveWrapper implements Serializable {

	private static final long serialVersionUID = -2434862339757379671L;

	@SerializedName("materia")
	private MateriaDTO materiaDTO;

	public MateriaDTO getMateriaDTO() {
		return materiaDTO;
	}

	public void setMateriaDTO(MateriaDTO materiaDTO) {
		this.materiaDTO = materiaDTO;
	}
	
	
}
