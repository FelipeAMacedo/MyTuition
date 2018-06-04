package com.felipemacedo.mytuition.dto.save.wrapper;

import com.felipemacedo.mytuition.dto.disciplina.DisciplinaDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DisciplinaSaveWrapper implements Serializable {

	private static final long serialVersionUID = -4920205047270404229L;

	@SerializedName("disciplina")
	private DisciplinaDTO disciplinaDTO;

	public DisciplinaDTO getDisciplinaDTO() {
		return disciplinaDTO;
	}

	public void setDisciplinaDTO(DisciplinaDTO disciplinaDTO) {
		this.disciplinaDTO = disciplinaDTO;
	}

}
