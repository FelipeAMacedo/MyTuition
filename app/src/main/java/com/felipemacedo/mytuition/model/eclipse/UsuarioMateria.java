package com.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;
import java.time.LocalDateTime;


public class UsuarioMateria implements Serializable {

    private UsuarioMateriaId id;
    private LocalDateTime inicio;
    private LocalDateTime conclusao;
    private Usuario usuario;
    private Materia materia;

    public UsuarioMateriaId getId() {
        return id;
    }

    public void setId(UsuarioMateriaId id) {
        this.id = id;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getConclusao() {
        return conclusao;
    }

    public void setConclusao(LocalDateTime conclusao) {
        this.conclusao = conclusao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

}
