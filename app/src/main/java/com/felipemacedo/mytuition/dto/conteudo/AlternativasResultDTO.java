package com.felipemacedo.mytuition.dto.conteudo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlternativasResultDTO implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("ordem")
    private Integer ordem;

    @SerializedName("texto")
    private String texto;

    @SerializedName("certo")
    private Boolean certo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getCerto() {
        return certo;
    }

    public void setCerto(Boolean certo) {
        this.certo = certo;
    }

}
