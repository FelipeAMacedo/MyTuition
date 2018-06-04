package com.felipemacedo.mytuition.dto.login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AtaqueResponseDTO implements Serializable {

    private static final long serialVersionUID = 5661921591956567863L;

    @SerializedName("data")
    private LocalDateTime data;

    @SerializedName("periodo")
    private Integer periodo;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

}
