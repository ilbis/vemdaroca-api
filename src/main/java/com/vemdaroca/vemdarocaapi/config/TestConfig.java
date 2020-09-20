package com.vemdaroca.vemdarocaapi.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.model.ItemPedido;
import com.vemdaroca.vemdarocaapi.model.Pedido;
import com.vemdaroca.vemdarocaapi.model.Produto;
import com.vemdaroca.vemdarocaapi.model.Role;
import com.vemdaroca.vemdarocaapi.model.UnidMedida;
import com.vemdaroca.vemdarocaapi.repository.ClienteRepository;
import com.vemdaroca.vemdarocaapi.repository.ItemPedidoRepository;
import com.vemdaroca.vemdarocaapi.repository.PedidoRepository;
import com.vemdaroca.vemdarocaapi.repository.ProdutoRepository;
import com.vemdaroca.vemdarocaapi.repository.RoleRepository;
import com.vemdaroca.vemdarocaapi.security.PasswordUtils;

@Configuration
@Profile("local")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {

		String myPassword = "SnIwMjAyMTk5Mw==";
		byte[] decodedBytes = Base64.getDecoder().decode(myPassword);
		String passwordNew = new String(decodedBytes);
		String mySecurePassword = PasswordUtils.generateSecurePassword(passwordNew, ConfigConstants.SALT);
		
//		String salt = PasswordUtils.getSalt(30);
//		String mySecurePassword = PasswordUtils.generateSecurePassword(myPassword, salt);
//		System.out.println("My secure password = " + mySecurePassword);
//		System.out.println("Salt value = " + salt);
//
//		String providedPassword = "teste123";
//
//		boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, mySecurePassword, salt);
//
//		if (passwordMatch) {
//			System.out.println("Provided user password " + providedPassword + " is correct.");
//		} else {
//			System.out.println("Provided password is incorrect");
//		}

		Role ro1 = new Role("ROLE_ADMIN");
		Role ro2 = new Role("ROLE_USER");
		
		roleRepository.saveAll(Arrays.asList(ro1,ro2));
		
		Produto pr1 = new Produto(null, "Alface", "Verdura", 2.00, UnidMedida.UNIDADE, 'A');

		produtoRepository.saveAll(Arrays.asList(pr1));

		Cliente cl1 = new Cliente(null, "Ana", "18-98282-1212", "Rua Saracura", "11", "", "", "SP", "01111-111",
				"Cidade Dutra", "", "ana@gmail.com", 'A', "ana", mySecurePassword, "ROLE_USER");

		Cliente cl2 = new Cliente(null, "Beatriz", "18-98282-1212", "Rua Jose maximo", "11", "", "", "SP", "01111-111",
				"Cidade Dutra", "", "ana@gmail.com", 'A', "junior_9119", mySecurePassword, "ROLE_ADMIN");

		Pedido pe1 = new Pedido(null, Instant.now(), 'A', cl2);

		Pedido pe2 = new Pedido(null, Instant.now(), 'A', cl1);

		Pedido pe3 = new Pedido(null, Instant.now(), 'A', cl1);

		clienteRepository.saveAll(Arrays.asList(cl1, cl2));

		pedidoRepository.saveAll(Arrays.asList(pe1, pe2, pe3));

		ItemPedido it1 = new ItemPedido(pe1, pr1, 10.00, pr1.getValor(), 'A');

		itemPedidoRepository.saveAll(Arrays.asList(it1));

	}
}
