package com.felipemacedo.mytuition.dto.login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HeroiResponseDTO implements Serializable {

    private static final long serialVersionUID = 8121810391676220L;

    @SerializedName("id")
    private Long id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("imagem")
    private String imagem;

    @SerializedName("xp")
    private Integer xp;

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
