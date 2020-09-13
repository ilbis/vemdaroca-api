package com.vemdaroca.vemdarocaapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	@Id
	private String nameRole;
//
//	@ManyToMany(mappedBy = "roles")
//	private List<Cliente> clientes;

	@Override
	public String getAuthority() {
		return this.nameRole;
	}

	public Role() {
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
