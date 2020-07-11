package com.vemdaroca.vemdarocaapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.model.Produto;
import com.vemdaroca.vemdarocaapi.model.UnidMedida;
import com.vemdaroca.vemdarocaapi.repository.ClienteRepository;
import com.vemdaroca.vemdarocaapi.repository.ProdutoRepository;

@Configuration
@Profile("local")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public void run(String... args) throws Exception {

		Cliente c1 = new Cliente(null, "Ana", "18-98282-1212", "Rua Saracura", "11", "", "", "SP", "01111-111",
				"Cidade Dutra", "", "ana@gmail.com");

		Produto p1 = new Produto(null, "Alface", "Verdura", 2.00F, UnidMedida.UNIDADE);

		clienteRepository.saveAll(Arrays.asList(c1));
		produtoRepository.saveAll(Arrays.asList(p1));

	}
}
