package com.vemdaroca.vemdarocaapi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.net.HttpHeaders;
import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.model.DataRelatorio;
import com.vemdaroca.vemdarocaapi.model.Pedido;
import com.vemdaroca.vemdarocaapi.service.PedidoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping(value = "allActive")
	@ApiOperation(value = "Retorna todos pedidos ativos")
	public ResponseEntity<List<Pedido>> getAllActive() {
		return ResponseEntity.ok().body(pedidoService.getAllActive());
	}

//	@GetMapping(value = "/all")
//	@ApiOperation(value = "Retorna todos pedidos")
//	public ResponseEntity<List<Pedido>> getAll() {
//		return ResponseEntity.ok().body(pedidoService.getAll());
//	}
//
//	@GetMapping(value = "/{id}")
//	@ApiOperation(value = "Retorna pedido por Id")
//	public ResponseEntity<Pedido> getById(@PathVariable Long id) {
//		return ResponseEntity.ok().body(pedidoService.getById(id));
//	}
//
//	@GetMapping(value = "/getPedido")
//	@ApiOperation(value = "Retorna todos pedidos por cliente")
//	public ResponseEntity<List<Pedido>> getByCliente(
//			@RequestParam(value = "clienteId", required = false) Long clienteId) {
//		return ResponseEntity.ok().body(pedidoService.getByIdCliente(clienteId));
//	}
//
//	@PostMapping
//	@ApiOperation(value = "Criar um pedido")
//	public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
//
//		Authentication x = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println("ID: " + x.getPrincipal());
//
//		Cliente cliente = new Cliente();
//		cliente.setId(Long.parseLong(x.getPrincipal().toString()));
//		pedido.setCliente(cliente);
//
//		pedido = pedidoService.create(pedido);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
//		return ResponseEntity.created(uri).body(pedido);
//	}
//
//	@DeleteMapping(value = "/{id}")
//	@ApiOperation(value = "Apaga um pedido")
//	public ResponseEntity<Pedido> delete(@PathVariable Long id) {
//		pedidoService.delete(id);
//		return ResponseEntity.noContent().build();
//	}
//
//	@PutMapping(value = "/{id}")
//	@ApiOperation(value = "Atualiza um pedido")
//	public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedido) {
//		return ResponseEntity.ok().body(pedidoService.update(id, pedido));
//	}

	@PostMapping(value = "/relatorio")
	@ResponseBody
	public ResponseEntity<InputStreamResource> downloadFile(@Param(value = "id") Long id,
			@RequestBody DataRelatorio data) throws FileNotFoundException {

		File file = pedidoService.gerarRelatorio(data);

		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				.contentType(MediaType.APPLICATION_XML).contentLength(file.length()) //
				.body(resource);
	}

}
