package com.felipemacedo.mytuition.dto.usuarioConquista;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class UsuarioConquistaDTO implements Serializable {

	@SerializedName("usuario")
    private UsuarioConquistaUsuarioDTO usuario;

    @SerializedName("conquista")
    private UsuarioConquistaConquistaDTO conquista;

    @SerializedName("dataConquista")
    private LocalDate dataConquista;

    public UsuarioConquistaUsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioConquistaUsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public UsuarioConquistaConquistaDTO getConquista() {
        return conquista;
    }

    public void setConquista(UsuarioConquistaConquistaDTO conquista) {
        this.conquista = conquista;
    }

    public LocalDate getDataConquista() {
        return dataConquista;
    }

    public void setDataConquista(LocalDate dataConquista) {
        this.dataConquista = dataConquista;
    }
}
