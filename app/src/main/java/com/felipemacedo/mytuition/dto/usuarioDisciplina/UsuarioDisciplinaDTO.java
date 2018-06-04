package com.example.felipemacedo.mytuition.dto.usuarioDisciplina;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UsuarioDisciplinaDTO implements Serializable {

    private static final long serialVersionUID = -5427400437011577513L;

    @SerializedName("usuario")
    private UsuarioDisciplinaUsuarioDTO usuario;

    @SerializedName("disciplina")
    private UsuarioDisciplinaDisciplinaDTO disciplina;

    @SerializedName("inicio")
    private LocalDateTime inicio;

    @SerializedName("conclusao")
    private LocalDateTime conclusao;

    public UsuarioDisciplinaUsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDisciplinaUsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public UsuarioDisciplinaDisciplinaDTO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(UsuarioDisciplinaDisciplinaDTO disciplina) {
        this.disciplina = disciplina;
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

}
