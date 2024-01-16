package com.vemdaroca.vemdarocaapi.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.model.ItemPedido;
import com.vemdaroca.vemdarocaapi.model.Pedido;
import com.vemdaroca.vemdarocaapi.repository.ItemPedidoRepository;
import com.vemdaroca.vemdarocaapi.repository.PedidoRepository;

@Component(value = "itemService")
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	public List<ItemPedido> getAllActive() {
		return itemPedidoRepository.findAllStatusActive();
	}

	public List<ItemPedido> getAll() {
		return itemPedidoRepository.findAll();
	}

	public ItemPedido getById(Long id) {
		return itemPedidoRepository.findById(id).get();
	}

	public ItemPedido create(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}

	@Transactional
	public Pedido createAll(List<ItemPedido> itemPedido) {
		Authentication x = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("ID: " + x.getPrincipal());

		Cliente cliente = new Cliente();
		Pedido pedido = new Pedido();
		cliente.setId(Long.parseLong(x.getPrincipal().toString()));
		pedido.setCliente(cliente);
		pedido.setMoment(Instant.now());
		pedido.setStatus('A');
		Pedido pedidoResponse = pedidoRepository.save(pedido);
		itemPedido.forEach(item -> {
			item.getPedido().setId(pedidoResponse.getId());
		});
		itemPedidoRepository.saveAll(itemPedido);
		return pedidoResponse;
	}

	public ItemPedido delete(Long id) {
		ItemPedido entity = itemPedidoRepository.findById(id).get();
		entity.setStatus('I');
		return itemPedidoRepository.save(entity);
	}

	public ItemPedido update(Long id, ItemPedido itemPedido) {
		ItemPedido entity = itemPedidoRepository.findById(id).get();
		updateData(entity, itemPedido);
		return itemPedidoRepository.save(entity);
	}

	private void updateData(ItemPedido entity, ItemPedido itemPedido) {
		entity.setQtd(itemPedido.getQtd());
		entity.setStatus(itemPedido.getStatus());
		entity.setValor(itemPedido.getValor());
	}
}
