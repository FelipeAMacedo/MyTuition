package com.example.felipemacedo.mytuition.model.eclipse;

import java.io.Serializable;
import java.time.LocalDate;


public class Aluno implements Serializable {

    private static final long serialVersionUID = 2749373371537870295L;
    private String cpf;

    private String nome;
    private String ra;
    private LocalDate dataNascimento;

    private LocalDate dataEntrada;

    private Boolean trabalhaArea;
    private Boolean sexo;

//	@OneToOne
//	private Usuario usuario;

    private Curso curso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Boolean getTrabalhaArea() {
        return trabalhaArea;
    }

    public void setTrabalhaArea(Boolean trabalhaArea) {
        this.trabalhaArea = trabalhaArea;
    }

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
