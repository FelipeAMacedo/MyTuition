package com.felipemacedo.mytuition.model;

import java.io.Serializable;

/**
 * Created by felipemacedo on 16/10/17.
 */

public class Usuario implements Serializable {

    public String nomeCompleto;
    public String nomeHeroi;
    public long ra;
    public long dataNascimento;
    public boolean sexo;
    public String email;
    public String senha;
    public int level = 1;
    public int xp = 0;

    public Usuario() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Usuario (String nomeCompleto, String nomeHeroi, long ra, long dataNascimento, boolean sexo, String email, String senha, int level, int xp) {
        this.nomeCompleto = nomeCompleto;
        this.nomeHeroi = nomeHeroi;
        this.ra = ra;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
        this.level = level;
        this.xp = xp;
    }
}
