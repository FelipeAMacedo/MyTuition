package com.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UsuarioDisciplina implements Serializable {

    private static final long serialVersionUID = 1486104259405026606L;
    private UsuarioDisciplinaId id;
    private LocalDateTime inicio;
    private LocalDateTime conclusao;
    private Usuario usuario;
    private Disciplina disciplina;

    public UsuarioDisciplinaId getId() {
        return id;
    }

    public void setId(UsuarioDisciplinaId id) {
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

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

}
