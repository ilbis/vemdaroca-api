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
import com.vemdaroca.vemdarocaapi.model.Tipo;
import com.vemdaroca.vemdarocaapi.model.UnidMedida;
import com.vemdaroca.vemdarocaapi.repository.ClienteRepository;
import com.vemdaroca.vemdarocaapi.repository.ItemPedidoRepository;
import com.vemdaroca.vemdarocaapi.repository.PedidoRepository;
import com.vemdaroca.vemdarocaapi.repository.ProdutoRepository;
import com.vemdaroca.vemdarocaapi.security.PasswordUtils;
import com.vemdaroca.vemdarocaapi.service.EmailService;
import com.vemdaroca.vemdarocaapi.service.ExcelService;
import com.vemdaroca.vemdarocaapi.util.CommandLineUtil;

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
	private ExcelService excelService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private CommandLineUtil commandLineUtil;

	@SuppressWarnings("deprecation")
	@Override
	public void run(String... args) throws Exception {

		System.out.println("Enviando email");

		String myPassword = "SnIwMjAyMTk5Mw==";
		byte[] decodedBytes = Base64.getDecoder().decode(myPassword);
		String passwordNew = new String(decodedBytes);
		String mySecurePassword = PasswordUtils.generateSecurePassword(passwordNew, ConfigConstants.SALT);

		Produto pr1 = new Produto(null, "Batata", Tipo.TUBERCULO, 8.50, UnidMedida.KILO, 'A');
		Produto pr2 = new Produto(null, "Abobrinha 45 dias - Italiana", Tipo.FRUTA, 3.90, UnidMedida.KILO, 'A');
		Produto pr3 = new Produto(null, "Acelga", Tipo.VERDURA, 4.90, UnidMedida.MAÇO, 'A');
		Produto pr4 = new Produto(null, "Agrião", Tipo.VERDURA, 4.50, UnidMedida.MAÇO, 'A');
		Produto pr5 = new Produto(null, "Alface Americana", Tipo.VERDURA, 4.00, UnidMedida.UNIDADE, 'A');
		Produto pr6 = new Produto(null, "Alface Crespa", Tipo.VERDURA, 3.50, UnidMedida.UNIDADE, 'A');
		Produto pr7 = new Produto(null, "Alface Lisa", Tipo.VERDURA, 3.50, UnidMedida.UNIDADE, 'A');
		Produto pr8 = new Produto(null, "Alface Mimosa", Tipo.VERDURA, 3.50, UnidMedida.UNIDADE, 'A');
		Produto pr9 = new Produto(null, "Alface Roxa", Tipo.VERDURA, 4.00, UnidMedida.UNIDADE, 'A');
		Produto pr10 = new Produto(null, "Almeirão", Tipo.VERDURA, 3.50, UnidMedida.MAÇO, 'A');
		Produto pr11 = new Produto(null, "Alho ", Tipo.TUBERCULO, 28.00, UnidMedida.KILO, 'A');
		Produto pr12 = new Produto(null, "Alho Poro", Tipo.VERDURA, 4.90, UnidMedida.MAÇO, 'A');
		Produto pr13 = new Produto(null, "Abobora Pescoço", Tipo.FRUTA, 4.00, UnidMedida.KILO, 'A');

		System.out.println("Imprimindo:" + Role.ROLE_ADMIN);

		produtoRepository.saveAll(Arrays.asList(pr1, pr2, pr3, pr4, pr5, pr6, pr7, pr8, pr9, pr10, pr11, pr12, pr13));

		Cliente cl1 = new Cliente(null, "Jorge", "18-98282-1212", "Rua Saracura", "11", "Rua 1", "Sao Paulo", "SP",
				"01111-111", "Cidade Dutra", "jorge@gmail.com", 'A', "jorge_admin",
				"Vhh8pw1Szp6MRdk1mTHaCtjlMCiP7kkpS7m/gS+2ZSU=", "1234", Role.ROLE_ADMIN);

		Cliente cl2 = new Cliente(null, "Ilbis", "11-92290939", "Rua Jose maximo", "11", "Rua 2", "Sao Paulo", "SP",
				"01111-111", "Cidade Dutra", "ilbis.candido@gmail.com", 'A', "ilbis", mySecurePassword, "1234",
				Role.ROLE_ADMIN);

		Pedido pe1 = new Pedido(null, Instant.now(), 'A', cl2);

		Pedido pe2 = new Pedido(null, Instant.now(), 'A', cl1);

		Pedido pe3 = new Pedido(null, Instant.now(), 'A', cl2);

		Pedido pe4 = new Pedido(null, Instant.now(), 'A', cl1);

		Pedido pe5 = new Pedido(null, Instant.now(), 'A', cl2);

		clienteRepository.saveAll(Arrays.asList(cl1, cl2));

		pedidoRepository.saveAll(Arrays.asList(pe1, pe2, pe3));

		ItemPedido it1 = new ItemPedido(pe1, pr1, 10.00, pr1.getValor(), 'A');
		ItemPedido it2 = new ItemPedido(pe1, pr2, 10.00, pr2.getValor(), 'A');
		ItemPedido it3 = new ItemPedido(pe1, pr3, 10.00, pr3.getValor(), 'A');
		ItemPedido it4 = new ItemPedido(pe2, pr4, 10.00, pr4.getValor(), 'A');
		ItemPedido it5 = new ItemPedido(pe2, pr5, 10.00, pr5.getValor(), 'A');
		ItemPedido it6 = new ItemPedido(pe2, pr6, 10.00, pr6.getValor(), 'A');
		ItemPedido it7 = new ItemPedido(pe3, pr7, 10.00, pr7.getValor(), 'A');
		ItemPedido it8 = new ItemPedido(pe3, pr8, 10.00, pr8.getValor(), 'A');
		ItemPedido it9 = new ItemPedido(pe3, pr9, 10.00, pr9.getValor(), 'A');
		ItemPedido it10 = new ItemPedido(pe3, pr10, 10.00, pr10.getValor(), 'A');

		itemPedidoRepository.saveAll(Arrays.asList(it1, it2, it3, it4, it5, it6, it7, it8, it9, it10));

//		String command = "curl -fsSL -o /tmp/Tabela.xlsx https://docs.google.com/spreadsheets/d/e/2PACX-1vSWxfvK3Qk0w4Tx9LAboQJa850J-pZK56wR0QbyiYeNnyZBXb169toQKDlSYmDwLdzcnEpbOgiXqxpI/pub?output=xlsx";
//		CommandReturnDTO response = commandLineUtil.executeCommandLine("/tmp", command);
//		System.out.println(response.getLogError());
//		excelService.AddRegistroExcel(ClienteResponseDTO.toDTO(cl2),
//				Arrays.asList(it1, it2, it3, it4, it5, it6, it7, it8, it9, it10), "/tmp/Tabela.xlsx");
	}
}
