package com.example.felipemacedo.mytuition.dto.materia;

import com.example.felipemacedo.mytuition.dto.usuarioMateria.UsuarioMateriaDTO;
import com.example.felipemacedo.mytuition.enums.Atributo;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

public class MateriaResultDTO implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("pontos")
    private Integer pontos;

    @SerializedName("atributo")
    private Atributo atributo;

    @SerializedName("usuarioMateria")
    private Set<UsuarioMateriaDTO> usuarioMateriaDTO;

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

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    public Set<UsuarioMateriaDTO> getUsuarioMateriaDTO() {
        return usuarioMateriaDTO;
    }

    public void setUsuarioMateriaDTO(Set<UsuarioMateriaDTO> usuarioMateriaDTO) {
        this.usuarioMateriaDTO = usuarioMateriaDTO;
    }
}
