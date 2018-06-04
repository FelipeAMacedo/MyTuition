package com.felipemacedo.mytuition.dto;

import com.felipemacedo.mytuition.enums.Perfil;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 7700278570593873759L;

	@SerializedName("email")
	private String email;

	@SerializedName("senha")
	private String senha;

	@SerializedName("perfil")
	private Perfil perfil;

	@SerializedName("informacoesPessoais")
	private AlunoDTO alunoDTO;

	@SerializedName("heroi")
	private HeroiDTO heroiDTO;

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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public AlunoDTO getAlunoDTO() {
		return alunoDTO;
	}

	public void setAlunoDTO(AlunoDTO alunoDTO) {
		this.alunoDTO = alunoDTO;
	}

	public HeroiDTO getHeroiDTO() {
		return heroiDTO;
	}

	public void setHeroiDTO(HeroiDTO heroiDTO) {
		this.heroiDTO = heroiDTO;
	}

}
