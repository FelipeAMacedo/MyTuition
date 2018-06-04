package com.felipemacedo.mytuition.dto.conquista;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ConquistaBuscaDTO implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("dataAlteracao")
    private LocalDateTime dataAlteracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
