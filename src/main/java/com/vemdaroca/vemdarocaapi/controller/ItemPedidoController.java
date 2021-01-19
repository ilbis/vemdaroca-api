package com.vemdaroca.vemdarocaapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vemdaroca.vemdarocaapi.dto.ClienteResponseDTO;
import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.model.ItemPedido;
import com.vemdaroca.vemdarocaapi.model.Pedido;
import com.vemdaroca.vemdarocaapi.service.ClienteService;
import com.vemdaroca.vemdarocaapi.service.EmailService;
import com.vemdaroca.vemdarocaapi.service.ItemPedidoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/itempedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "allActive")
	@ApiOperation(value = "Retorna todos itens de pedido ativos")
	public ResponseEntity<List<ItemPedido>> getAllActive() {
		return ResponseEntity.ok().body(itemPedidoService.getAllActive());
	}

	@GetMapping(value = "/all")
	@ApiOperation(value = "Retorna todos itens de pedido")
	public ResponseEntity<List<ItemPedido>> getAll() {
		return ResponseEntity.ok().body(itemPedidoService.getAll());
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retorna item do pedido por Id")
	public ResponseEntity<ItemPedido> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(itemPedidoService.getById(id));
	}

	@PostMapping
	@ApiOperation(value = "Criar um item de pedido")
	public ResponseEntity<ItemPedido> create(@RequestBody ItemPedido itemPedido) {
		itemPedido = itemPedidoService.create(itemPedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itemPedido.getId())
				.toUri();
		return ResponseEntity.created(uri).body(itemPedido);
	}

	@PostMapping(value = "/createAll")
	@ApiOperation(value = "Criar uma lista de item de pedido")
	public ResponseEntity<Pedido> createAll(@RequestBody List<ItemPedido> itemPedido) throws Exception {
		Pedido pedido = itemPedidoService.createAll(itemPedido);

		try {
			Authentication x = SecurityContextHolder.getContext().getAuthentication();
			ClienteResponseDTO cliente = clienteService.getById(Long.parseLong(x.getPrincipal().toString()));

			System.out.println("EMAIL: " + cliente.getEmail());
			emailService.sendMail(cliente.getEmail(), "VEM DA ROÇA - PEDIDO",
					itemPedidoService.formatedPedidoEmail(itemPedido));
		} catch (Exception e) {
			System.out.println("Erro ao enviar email: " + e);
			throw new Exception(e);
		}
		return ResponseEntity.created(null).body(pedido);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Apaga um item de pedido")
	public ResponseEntity<ItemPedido> delete(@PathVariable Long id) {
		itemPedidoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualiza um item de pedido")
	public ResponseEntity<ItemPedido> update(@PathVariable Long id, @RequestBody ItemPedido itemPedido) {
		return ResponseEntity.ok().body(itemPedidoService.update(id, itemPedido));
	}
}
