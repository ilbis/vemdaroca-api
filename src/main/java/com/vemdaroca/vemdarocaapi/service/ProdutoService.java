package com.vemdaroca.vemdarocaapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vemdaroca.vemdarocaapi.model.Produto;
import com.vemdaroca.vemdarocaapi.repository.ProdutoRepository;

@Component(value = "produtoService")
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> getAllActive() {
		return produtoRepository.findAllStatusActive();
	}

	public List<Produto> getAll() {
		return produtoRepository.findAll();
	}

	public Produto getById(Long id) {
		return produtoRepository.findById(id).get();
	}

	public List<Produto> getByName(String nome) {
		return produtoRepository.findByName(nome);
	}

	public List<Produto> getByTipo(String tipo) {
		return produtoRepository.findByTipo(tipo);
	}

	public Produto create(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto delete(Long id) {
		Produto entity = produtoRepository.findById(id).get();
		entity.setStatus('I');
		return produtoRepository.save(entity);
	}

	public Produto update(Long id, Produto produto) {
		Produto entity = produtoRepository.findById(id).get();
		updateData(entity, produto);
		return produtoRepository.save(entity);
	}

	private void updateData(Produto entity, Produto produto) {
		entity.setNome(produto.getNome());
		entity.setStatus(produto.getStatus());
		entity.setTipo(produto.getTipo());
		entity.setUnidMedida(produto.getUnidMedida());
		entity.setValor(produto.getValor());
	}

}
