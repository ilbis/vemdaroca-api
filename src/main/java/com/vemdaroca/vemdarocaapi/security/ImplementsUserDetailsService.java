package com.vemdaroca.vemdarocaapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.service.ClienteService;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {

	@Autowired
	private ClienteService clienteService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente cliente = clienteService.getByUserName(username);

		if (cliente == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}

		return cliente;
	}

}
