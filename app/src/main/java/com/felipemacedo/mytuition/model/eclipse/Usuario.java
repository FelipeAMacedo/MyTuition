package com.felipemacedo.mytuition.model.eclipse;

import com.felipemacedo.mytuition.enums.Perfil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Usuario implements Serializable {

    private static final long serialVersionUID = -8533122513715515843L;

    private String email;
    private String senha;
    private Heroi heroi;
    private Aluno aluno;
    private Perfil perfil = Perfil.ALUNO;
    private Set<Ataque> ataque;
    private Set<UsuarioConteudo> usuarioConteudo = new HashSet<>(0);
    private Set<UsuarioConquista> usuarioConquista = new HashSet<>(0);
    private Set<UsuarioDisciplina> usuarioDisciplina = new HashSet<>(0);

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Heroi getHeroi() {
        return heroi;
    }

    public void setHeroi(Heroi heroi) {
        this.heroi = heroi;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Set<Ataque> getAtaque() {
        return ataque;
    }

    public void setAtaque(Set<Ataque> ataque) {
        this.ataque = ataque;
    }

    public Set<UsuarioConteudo> getUsuarioConteudo() {
        return usuarioConteudo;
    }

    public void setUsuarioConteudo(Set<UsuarioConteudo> usuarioConteudo) {
        this.usuarioConteudo = usuarioConteudo;
    }

    public Set<UsuarioConquista> getUsuarioConquista() {
        return usuarioConquista;
    }

    public void setUsuarioConquista(Set<UsuarioConquista> usuarioConquista) {
        this.usuarioConquista = usuarioConquista;
    }

    public Set<UsuarioDisciplina> getUsuarioDisciplina() {
        return usuarioDisciplina;
    }

    public void setUsuarioDisciplina(Set<UsuarioDisciplina> usuarioDisciplina) {
        this.usuarioDisciplina = usuarioDisciplina;
    }
}
