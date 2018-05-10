package com.example.felipemacedo.mytuition.dto.login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HeroiResponseDTO implements Serializable {

    private static final long serialVersionUID = 8121810391676220L;

    @SerializedName("nome")
    private String nome;

    @SerializedName("imagem")
    private String imagem;

    @SerializedName("xp")
    private Integer xp;

    @SerializedName("forca")
    private Integer forca;

    @SerializedName("agilidade")
    private Integer agilidade;

    @SerializedName("defesa")
    private Integer defesa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(Integer agilidade) {
        this.agilidade = agilidade;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }

}
