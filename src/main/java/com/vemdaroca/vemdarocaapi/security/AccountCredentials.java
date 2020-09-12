package com.vemdaroca.vemdarocaapi.security;

import java.util.Base64;

import com.vemdaroca.vemdarocaapi.config.ConfigConstants;
import com.vemdaroca.vemdarocaapi.security.PasswordUtils;
import org.springframework.beans.factory.annotation.Value;

public class AccountCredentials {

	private String username;
	private String password;

	public AccountCredentials() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		byte[] decodedBytes = Base64.getDecoder().decode(password);
		String passwordNew = new String(decodedBytes);
		this.password = PasswordUtils.generateSecurePassword(passwordNew, ConfigConstants.SALT);
	}

}
