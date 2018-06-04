package com.felipemacedo.mytuition.dto.usuarioMateria;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UsuarioMateriaDTO implements Serializable {

    private static final long serialVersionUID = -5427400437011577513L;

    @SerializedName("usuario")
    private UsuarioMateriaUsuarioDTO usuario;

    @SerializedName("materia")
    private UsuarioMateriaMateriaDTO materia;

    @SerializedName("inicio")
    private LocalDateTime inicio;

    @SerializedName("conclusao")
    private LocalDateTime conclusao;

    public UsuarioMateriaUsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioMateriaUsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public UsuarioMateriaMateriaDTO getMateria() {
        return materia;
    }

    public void setMateria(UsuarioMateriaMateriaDTO materia) {
        this.materia = materia;
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
