package com.vemdaroca.vemdarocaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/all")
	@ApiOperation(value = "Retorna todos clientes")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		return ResponseEntity.ok().body(clienteService.getAllClientes());
	}

	@PostMapping
	@ApiOperation(value = "Criar um cliente")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		return ResponseEntity.ok().body(clienteService.createCliente(cliente));
	}
	
//	@PostMapping
//	@ApiOperation(value = "Apaga um cliente")
//	public ResponseEntity<Cliente> deleteCliente(@RequestBody Long id) {
//		return ResponseEntity.ok().body(clienteService.deleteCliente(id));
//	}

}
