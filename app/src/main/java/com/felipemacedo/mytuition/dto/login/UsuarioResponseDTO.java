package com.felipemacedo.mytuition.dto.login;

import com.felipemacedo.mytuition.enums.Perfil;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioResponseDTO implements Serializable {

	private static final long serialVersionUID = 7700278570593873759L;

	@SerializedName("email")
	private String email;

	@SerializedName("senha")
	private String senha;

	@SerializedName("perfil")
	private Perfil perfil;

	@SerializedName("heroi")
	private HeroiResponseDTO heroiResponseDTO;

	@SerializedName("ataque")
	private AtaqueResponseDTO ataqueResponseDTO;

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

	public HeroiResponseDTO getHeroiResponseDTO() {
		return heroiResponseDTO;
	}

	public void setHeroiResponseDTO(HeroiResponseDTO heroiResponseDTO) {
		this.heroiResponseDTO = heroiResponseDTO;
	}

	public AtaqueResponseDTO getAtaqueResponseDTO() {
		return ataqueResponseDTO;
	}

	public void setAtaqueResponseDTO(AtaqueResponseDTO ataqueResponseDTO) {
		this.ataqueResponseDTO = ataqueResponseDTO;
	}

}
