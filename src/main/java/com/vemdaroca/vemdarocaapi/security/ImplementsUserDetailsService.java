package com.vemdaroca.vemdarocaapi.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.repository.RoleRepository;
import com.vemdaroca.vemdarocaapi.service.ClienteService;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

	@Autowired
	private ClienteService clienteService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente cliente = clienteService.getByUserName(username);

		if (cliente == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
		return new User(cliente.getUsername(), cliente.getPassword(), true, true, true, true, cliente.getAuthorities());
	}

}
