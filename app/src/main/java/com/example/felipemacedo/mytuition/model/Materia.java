package com.example.felipemacedo.mytuition.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by felipemacedo on 07/10/17.
 */
@Entity(tableName = "tb_materias")
public class Materia implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titulo;

    @Ignore
    private List<Licao> licoes;

    public Materia() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
