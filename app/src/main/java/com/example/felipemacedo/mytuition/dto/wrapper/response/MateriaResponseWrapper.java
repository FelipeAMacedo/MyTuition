package com.example.felipemacedo.mytuition.dto.wrapper.response;

import com.example.felipemacedo.mytuition.dto.materia.MateriaResultDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

public class MateriaResponseWrapper implements Serializable {

    @SerializedName("materias")
    private Set<MateriaResultDTO> materias;

    public Set<MateriaResultDTO> getMaterias() {
        return materias;
    }

    public void setMaterias(Set<MateriaResultDTO> materias) {
        this.materias = materias;
    }
}
