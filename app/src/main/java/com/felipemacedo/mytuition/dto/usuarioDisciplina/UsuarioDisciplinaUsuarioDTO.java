package com.example.felipemacedo.mytuition.dto.usuarioDisciplina;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioDisciplinaUsuarioDTO implements Serializable {

    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
