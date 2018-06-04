package com.felipemacedo.mytuition.dto.materia;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DisciplinaDTO implements Serializable {

	private static final long serialVersionUID = 6563243374801542354L;

	@SerializedName("id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
