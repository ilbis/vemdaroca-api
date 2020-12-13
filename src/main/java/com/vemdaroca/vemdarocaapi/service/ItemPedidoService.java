package com.vemdaroca.vemdarocaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vemdaroca.vemdarocaapi.model.ItemPedido;
import com.vemdaroca.vemdarocaapi.repository.ItemPedidoRepository;

@Component(value = "itemService")
public class ItemPedidoService {

	String texto = "";

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

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

	public List<ItemPedido> createAll(List<ItemPedido> itemPedido) {
		return itemPedidoRepository.saveAll(itemPedido);
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

	public String formatedPedidoEmail(List<ItemPedido> itemPedido) {
		texto = "";

		itemPedido.forEach(item -> {
			
			texto += item.getProduto().toString() + item.toString();
		});
		return texto;
	}
}
