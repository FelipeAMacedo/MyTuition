package com.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Conquista implements Serializable {

    private static final long serialVersionUID = 6799346354339316468L;

    private Long id;

    private String nome;
    private String descricao;
    private String imagem;

    private Set<UsuarioConquista> usuarioConquista = new HashSet<>(0);

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

    public Set<UsuarioConquista> getUsuarioConquista() {
        return usuarioConquista;
    }

    public void setUsuarioConquista(Set<UsuarioConquista> usuarioConquista) {
        this.usuarioConquista = usuarioConquista;
    }
}
