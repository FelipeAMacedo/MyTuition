package com.example.felipemacedo.mytuition.dto.usuarioDisciplina;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioDisciplinaDisciplinaDTO implements Serializable {

    @SerializedName("id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
