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

	public List<Pedido> getAllClientes() {
		return pedidoRepository.findAll();
	}
}
