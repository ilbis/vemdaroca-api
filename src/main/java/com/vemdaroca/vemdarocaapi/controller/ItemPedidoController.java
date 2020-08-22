package com.vemdaroca.vemdarocaapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vemdaroca.vemdarocaapi.model.ItemPedido;
import com.vemdaroca.vemdarocaapi.service.ItemPedidoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/itempedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;

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
