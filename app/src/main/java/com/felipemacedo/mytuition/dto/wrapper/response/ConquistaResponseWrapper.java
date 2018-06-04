package com.example.felipemacedo.mytuition.dto.wrapper.response;

import com.felipemacedo.mytuition.dto.conquista.ConquistaResultDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

public class ConquistaResponseWrapper implements Serializable {

    @SerializedName("conquistas")
    private Set<ConquistaResultDTO> conquistas;

    public Set<ConquistaResultDTO> getConquistas() {
        return conquistas;
    }

    public void setConquistas(Set<ConquistaResultDTO> conquistas) {
        this.conquistas = conquistas;
    }
}
