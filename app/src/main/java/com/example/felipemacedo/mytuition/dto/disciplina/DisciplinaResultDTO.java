package com.example.felipemacedo.mytuition.dto.disciplina;

import com.example.felipemacedo.mytuition.dto.usuarioDisciplina.UsuarioDisciplinaDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

public class DisciplinaResultDTO implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("usuarioDisciplina")
    private Set<UsuarioDisciplinaDTO> usuarioDisciplinaDTO;

    public Long getid() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<UsuarioDisciplinaDTO> getUsuarioDisciplinaDTO() {
        return usuarioDisciplinaDTO;
    }

    public void setUsuarioDisciplinaDTO(Set<UsuarioDisciplinaDTO> usuarioDisciplinaDTO) {
        this.usuarioDisciplinaDTO = usuarioDisciplinaDTO;
    }
}
