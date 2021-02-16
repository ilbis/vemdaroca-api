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

import com.vemdaroca.vemdarocaapi.model.Produto;
import com.vemdaroca.vemdarocaapi.service.ProdutoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping(value = "/allActive")
	@ApiOperation(value = "Retorna todos produtos ativos")
	public ResponseEntity<List<Produto>> getAllActive() {
		return ResponseEntity.ok().body(produtoService.getAllActive());
	}

	@GetMapping(value = "/all")
	@ApiOperation(value = "Retorna todos produtos")
	public ResponseEntity<List<Produto>> getAllProdutos() {
		return ResponseEntity.ok().body(produtoService.getAll());
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retorna produto por Id")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(produtoService.getById(id));
	}

	@PostMapping
	@ApiOperation(value = "Criar um pedido")
	public ResponseEntity<Produto> create(@RequestBody Produto produto) {
		produto = produtoService.create(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(produto);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Apaga um produto")
	public ResponseEntity<Produto> delete(@PathVariable Long id) {
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualiza um produto")
	public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
		return ResponseEntity.ok().body(produtoService.update(id, produto));
	}
	
	@PutMapping(value = "/all")
	@ApiOperation(value = "Atualiza lista de produtos")
	public ResponseEntity<List<Produto>> updateAll(@RequestBody List<Produto> produtos) {
		return ResponseEntity.ok().body(produtoService.updateAll(produtos));
	}
}
