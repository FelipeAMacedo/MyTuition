package com.felipemacedo.mytuition.dto.conquista;

import com.felipemacedo.mytuition.dto.usuarioConquista.UsuarioConquistaResultDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public class ConquistaResultDTO implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("imagem")
    private String imagem;

    @SerializedName("dataCriacao")
    private LocalDateTime dataCriacao;

    @SerializedName("dataAlteracao")
    private LocalDateTime dataAlteracao;

    @SerializedName("usuarioConquista")
    private Set<UsuarioConquistaResultDTO> usuarioConquistaResultDTO;

    public Long getId() {
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Set<UsuarioConquistaResultDTO> getUsuarioConquistaResultDTO() {
        return usuarioConquistaResultDTO;
    }

    public void setUsuarioConquistaResultDTO(Set<UsuarioConquistaResultDTO> usuarioConquistaResultDTO) {
        this.usuarioConquistaResultDTO = usuarioConquistaResultDTO;
    }
}
