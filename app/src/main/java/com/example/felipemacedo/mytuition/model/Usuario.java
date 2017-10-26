package com.example.felipemacedo.mytuition.model;

import java.io.Serializable;

/**
 * Created by felipemacedo on 16/10/17.
 */

public class Usuario implements Serializable {

    public String username;
    public String email;
    public String senha;
    public int level;
    public int xp;

    public Usuario() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Usuario (String username, String email, String senha, int level, int xp) {
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.level = level;
        this.xp = xp;
    }
}
