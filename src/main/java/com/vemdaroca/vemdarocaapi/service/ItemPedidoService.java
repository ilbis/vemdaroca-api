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

	public String formatedPedidoEmail(List<ItemPedido> itemPedido) {
		StringBuilder returnText = new StringBuilder();

		Pedido pedido = pedidoRepository.findById(itemPedido.get(0).getPedido().getId()).get();
		Date moment = Date.from(pedido.getMoment());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT-3"));
		String dataFormatada = formatter.format(moment);

		returnText.append("Agradecemos seu pedido!! =D \n");
		returnText.append("Seu pedido foi solicitado com sucesso, abaixo está melhor detalhado: \n");
		returnText.append("Pedido: " + pedido.getId() + "\n");
		returnText.append("Data do pedido: " + dataFormatada + "\n");
		itemPedido.forEach(item -> {
			returnText.append("QTD: " + item.getQtd() + " \t\t Produto: " + item.getProduto().getNome()
					+ " \t\t\t\t\t Valor Unitário: R$" + item.getValor() + " \t\t\t\t\t SubTotal: R$"
					+ item.getSubTotal() + "\n");
		});
		returnText.append("Valor Médio Total: R$" + pedido.getTotal());
		return returnText.toString();
	}
}
