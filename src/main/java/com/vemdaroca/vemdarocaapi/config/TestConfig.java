package com.vemdaroca.vemdarocaapi.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.model.ItemPedido;
import com.vemdaroca.vemdarocaapi.model.Pedido;
import com.vemdaroca.vemdarocaapi.model.Produto;
import com.vemdaroca.vemdarocaapi.model.UnidMedida;
import com.vemdaroca.vemdarocaapi.repository.ClienteRepository;
import com.vemdaroca.vemdarocaapi.repository.ItemPedidoRepository;
import com.vemdaroca.vemdarocaapi.repository.PedidoRepository;
import com.vemdaroca.vemdarocaapi.repository.ProdutoRepository;
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

	@Override
	public void run(String... args) throws Exception {

		String myPassword = "teste123";
		String salt = PasswordUtils.getSalt(30);
		String mySecurePassword = PasswordUtils.generateSecurePassword(myPassword, salt);
		System.out.println("My secure password = " + mySecurePassword);
		System.out.println("Salt value = " + salt);

		String providedPassword = "teste123";

		boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, mySecurePassword, salt);

		if (passwordMatch) {
			System.out.println("Provided user password " + providedPassword + " is correct.");
		} else {
			System.out.println("Provided password is incorrect");
		}

		Cliente cl1 = new Cliente(null, "Ana", "18-98282-1212", "Rua Saracura", "11", "", "", "SP", "01111-111",
				"Cidade Dutra", "", "ana@gmail.com", 'A', "ana", mySecurePassword, salt);

		Cliente cl2 = new Cliente(null, "Anaa", "18-98282-1212", "Rua Saracura", "11", "", "", "SP", "01111-111",
				"Cidade Dutra", "", "ana@gmail.com", 'A', "ana", mySecurePassword, salt);

		Produto pr1 = new Produto(null, "Alface", "Verdura", 2.00F, UnidMedida.UNIDADE, 'A');

		Pedido pe1 = new Pedido(null, Instant.now(), 10.00F, cl2);

		ItemPedido it1 = new ItemPedido(null, pr1, pe1, 10F, 25.00F);

		clienteRepository.saveAll(Arrays.asList(cl1, cl2));
		produtoRepository.saveAll(Arrays.asList(pr1));
		pedidoRepository.saveAll(Arrays.asList(pe1));
		itemPedidoRepository.saveAll(Arrays.asList(it1));

	}
}
