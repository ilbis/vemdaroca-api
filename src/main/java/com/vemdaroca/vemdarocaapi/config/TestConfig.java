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
import com.vemdaroca.vemdarocaapi.security.PasswordUtils;
import com.vemdaroca.vemdarocaapi.service.EmailService;

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
    private EmailService emailService;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Enviando email");
//		emailService.sendMail("ilbis.candido@gmail.com", "Test subject", "Test mail");
		
		String myPassword = "SnIwMjAyMTk5Mw==";
		byte[] decodedBytes = Base64.getDecoder().decode(myPassword);
		String passwordNew = new String(decodedBytes);
		String mySecurePassword = PasswordUtils.generateSecurePassword(passwordNew, ConfigConstants.SALT);

		Produto pr1 = new Produto(null, "Alface", "Verdura", 2.00, UnidMedida.UNIDADE, 'A');
		Produto pr2 = new Produto(null, "Maçã", "Fruta", 10.00, UnidMedida.KILO, 'A');
		Produto pr3 = new Produto(null, "Melancia", "Fruta", 15.00, UnidMedida.UNIDADE, 'A');
		Produto pr4 = new Produto(null, "Chuchu", "Legumes", 3.00, UnidMedida.KILO, 'A');
		Produto pr5 = new Produto(null, "Suco de Frutas", "Suco", 10.00, UnidMedida.LITRO, 'A');
		Produto pr6 = new Produto(null, "Mamão", "Fruta", 5.00, UnidMedida.KILO, 'A');
		Produto pr7 = new Produto(null, "Pimentão", "Legumes", 11.00, UnidMedida.KILO, 'A');
		Produto pr8 = new Produto(null, "Abobrinha", "Legumes", 2.50, UnidMedida.KILO, 'A');
		Produto pr9 = new Produto(null, "Pera", "Fruta", 9.99, UnidMedida.KILO, 'A');
		Produto pr10 = new Produto(null, "Limão", "Fruta", 5.40, UnidMedida.KILO, 'A');
		Produto pr11 = new Produto(null, "Jaca", "Fruta", 12.80, UnidMedida.UNIDADE, 'A');
		Produto pr12 = new Produto(null, "Pepino", "Verdura", 3.50, UnidMedida.KILO, 'A');

		System.out.println("Imprimindo:" + Role.ROLE_ADMIN);

		produtoRepository.saveAll(Arrays.asList(pr1, pr2, pr3, pr4, pr5, pr6, pr7, pr8, pr9, pr10, pr11, pr12));

		Cliente cl1 = new Cliente(null, "Jorge", "18-98282-1212", "Rua Saracura", "11", "Rua 1", "Sao Paulo", "SP",
				"01111-111", "Cidade Dutra", "jorge@gmail.com", 'A', "jorge_admin",
				"Vhh8pw1Szp6MRdk1mTHaCtjlMCiP7kkpS7m/gS+2ZSU=", "1234", Role.ROLE_ADMIN);

		Cliente cl2 = new Cliente(null, "Beatriz", "18-98282-1212", "Rua Jose maximo", "11", "Rua 2", "Sao Paulo", "SP",
				"01111-111", "Cidade Dutra", "ana@gmail.com", 'A', "ilbis", mySecurePassword, "1234", Role.ROLE_ADMIN);

		Pedido pe1 = new Pedido(null, Instant.now(), 'A', cl2);

		Pedido pe2 = new Pedido(null, Instant.now(), 'A', cl1);

		Pedido pe3 = new Pedido(null, Instant.now(), 'A', cl1);

		clienteRepository.saveAll(Arrays.asList(cl1, cl2));

		pedidoRepository.saveAll(Arrays.asList(pe1, pe2, pe3));

		ItemPedido it1 = new ItemPedido(pe1, pr1, 10.00, pr1.getValor(), 'A');

		itemPedidoRepository.saveAll(Arrays.asList(it1));

	}
}
