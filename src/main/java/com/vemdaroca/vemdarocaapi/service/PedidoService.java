package com.vemdaroca.vemdarocaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vemdaroca.vemdarocaapi.model.Pedido;
import com.vemdaroca.vemdarocaapi.repository.PedidoRepository;

@Component(value = "pedidoService")
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	public List<Pedido> getAllActive() {
		return pedidoRepository.findAllStatusActive();
	}

	public List<Pedido> getAll() {
		return pedidoRepository.findAll();
	}

	public Pedido getById(Long id) {
		return pedidoRepository.findById(id).get();
	}

	public List<Pedido> getByIdCliente(Long id) {
		return pedidoRepository.findAllByCliente(id);
	}

	public Pedido create(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public Pedido delete(Long id) {
		Pedido entity = pedidoRepository.findById(id).get();
		entity.setStatus('I');
		return pedidoRepository.save(entity);
	}
	
	public Pedido update(Long id, Pedido pedido) {
		Pedido entity = pedidoRepository.findById(id).get();
		updateData(entity, pedido);
		return pedidoRepository.save(entity);
	}
	
	private void updateData(Pedido entity, Pedido pedido) {
		entity.setStatus(pedido.getStatus());
	}
}
