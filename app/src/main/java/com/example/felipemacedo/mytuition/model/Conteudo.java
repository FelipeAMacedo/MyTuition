package com.example.felipemacedo.mytuition.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.felipemacedo.mytuition.enums.TipoConteudo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by felipemacedo on 22/09/17.
 */

@Entity(tableName = "tb_conteudos")
public class Conteudo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titulo;
    private boolean completado;
    private String texto;

    @Ignore
    private TipoConteudo tipo;

    public Conteudo() { }

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

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public TipoConteudo getTipo() {
        return tipo;
    }

    public void setTipo(TipoConteudo tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        return this.titulo;
    }
}
