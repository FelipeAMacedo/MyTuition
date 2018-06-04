package com.example.felipemacedo.mytuition.dto.usuarioMateria;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioMateriaUsuarioDTO implements Serializable {

    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
