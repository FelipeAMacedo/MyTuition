package com.felipemacedo.mytuition.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by felipemacedo on 07/10/17.
 */
public class Materia implements Serializable {

    private String id;
    private String titulo;

    private List<Licao> licoes;

    public Materia() {}

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

    public List<Licao> getLicoes() {
        return licoes;
    }

    public void setLicoes(List<Licao> licoes) {
        this.licoes = licoes;
    }

    public String toString() {
        return this.titulo;
    }
}
