package com.felipemacedo.mytuition.dto.save.wrapper;

import com.felipemacedo.mytuition.dto.heroi.AumentarPontosDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AumentarPontosWrapper implements Serializable {

    @SerializedName("heroi")
    private AumentarPontosDTO aumentarPontosDTO;

    public AumentarPontosDTO getAumentarPontosDTO() {
        return aumentarPontosDTO;
    }

    public void setAumentarPontosDTO(AumentarPontosDTO aumentarPontosDTO) {
        this.aumentarPontosDTO = aumentarPontosDTO;
    }
}
