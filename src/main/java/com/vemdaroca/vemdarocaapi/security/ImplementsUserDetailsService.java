package com.vemdaroca.vemdarocaapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.repository.ClienteRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente cliente = clienteRepository.findByUserName(username).get();

		if (cliente == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
		
		String password = cliente.getPassword();
		String salt = cliente.getSalt();
		
//		String mySecurePassword = PasswordUtils.generateSecurePassword(password, salt);
//		System.out.println("My secure password = " + mySecurePassword);
//		System.out.println("Salt value = " + salt);

//		String providedPassword = "teste123";
//
//		boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, mySecurePassword, salt);
//
//		if (passwordMatch) {
//			System.out.println("Provided user password " + providedPassword + " is correct.");
//		} else {
//			System.out.println("Provided password is incorrect");
//		}
		
		return cliente;
	}

}
