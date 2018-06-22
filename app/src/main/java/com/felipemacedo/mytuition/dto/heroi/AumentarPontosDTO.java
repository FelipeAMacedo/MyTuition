package com.felipemacedo.mytuition.dto.heroi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AumentarPontosDTO implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("ataque")
    private Integer ataque;

    @SerializedName("defesa")
    private Integer defesa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAtaque() {
        return ataque;
    }

    public void setAtaque(Integer ataque) {
        this.ataque = ataque;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }
}
