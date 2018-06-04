package com.felipemacedo.mytuition.dto.save.wrapper;

import com.felipemacedo.mytuition.dto.LoginDTO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginWrapper implements Serializable {

	private static final long serialVersionUID = -8548942540740875853L;

	@SerializedName("usuario")
	private LoginDTO loginDTO;

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

}
