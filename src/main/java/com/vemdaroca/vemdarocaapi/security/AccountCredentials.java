package com.vemdaroca.vemdarocaapi.security;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.vemdaroca.vemdarocaapi.config.ConfigConstants;

public class AccountCredentials {

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

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

	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> role = new ArrayList<>();
		role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return role;
	}
}
