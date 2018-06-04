package com.example.felipemacedo.mytuition.dto.wrapper.response;

import com.example.felipemacedo.mytuition.dto.disciplina.DisciplinaResultDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

public class DisciplinaResponseWrapper implements Serializable {

    @SerializedName("disciplinas")
    private Set<DisciplinaResultDTO> disciplinas;

    public Set<DisciplinaResultDTO> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<DisciplinaResultDTO> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
