package com.example.felipemacedo.mytuition.dto.wrapper.response;

import com.felipemacedo.mytuition.dto.conteudo.ConteudoResultDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

public class ConteudoResponseWrapper implements Serializable {

    @SerializedName("conteudos")
    private Set<ConteudoResultDTO> conteudos;

    public Set<ConteudoResultDTO> getConteudos() {
        return conteudos;
    }

    public void setConteudos(Set<ConteudoResultDTO> conteudos) {
        this.conteudos = conteudos;
    }
}
