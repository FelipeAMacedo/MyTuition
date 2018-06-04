package com.example.felipemacedo.mytuition.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by felipemacedo on 22/09/17.
 */
public class Licao implements Serializable {

    private String id;
    private String titulo;
    private int conteudoCount;
    private int pontos;
    private Map<String, Long> usuarios = new HashMap<>();

    public Licao() {}

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getConteudoCount() {
        return conteudoCount;
    }

    public void setConteudoCount(int conteudoCount) {
        this.conteudoCount = conteudoCount;
    }

    public Map<String, Long> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(HashMap<String, Long> conteudos) {
        this.usuarios = usuarios;
    }

    public String toString() {
        return this.titulo;
    }
}
