package com.example.felipemacedo.mytuition.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by felipemacedo on 22/09/17.
 */
@Entity(tableName = "tb_licoes")
public class Licao implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titulo;

    @Ignore
    private List<Conteudo> conteudos;

    public Licao() {}

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

    public List<Conteudo> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }

    public String toString() {
        return this.titulo;
    }
}
