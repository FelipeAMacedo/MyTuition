package com.example.felipemacedo.mytuition.model;

import com.example.felipemacedo.mytuition.enums.TipoConteudo;

import java.io.Serializable;

/**
 * Created by felipemacedo on 22/09/17.
 */
public class Conteudo implements Serializable {

    private String id;
    private String titulo;
    private boolean completado;
    private String texto;

    private TipoConteudo tipo;

    public Conteudo() { }

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
