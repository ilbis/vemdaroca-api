package com.vemdaroca.vemdarocaapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	public Role() {
	}

	@Id
	private String nameRole;

	@Override
	public String getAuthority() {
		return this.nameRole;
	}

	public Role(String nameRole) {
		this.nameRole = nameRole;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

}
