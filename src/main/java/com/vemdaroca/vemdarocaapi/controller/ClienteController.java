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

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/allActive")
	@ApiOperation(value = "Retorna todos clientes ativos")
	public ResponseEntity<List<Cliente>> getAllActive() {
		return ResponseEntity.ok().body(clienteService.getAllActive());
	}

	@GetMapping(value = "/all")
	@ApiOperation(value = "Retorna todos clientes")
	public ResponseEntity<List<Cliente>> getAll() {
		return ResponseEntity.ok().body(clienteService.getAll());
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retorna cliente por Id")
	public ResponseEntity<Cliente> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(clienteService.getById(id));
	}

	@PostMapping
	@ApiOperation(value = "Criar um cliente")
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
		cliente = clienteService.create(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cliente);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Apaga um cliente")
	public ResponseEntity<Cliente> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualiza um cliente")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		return ResponseEntity.ok().body(clienteService.update(id, cliente));
	}

}
