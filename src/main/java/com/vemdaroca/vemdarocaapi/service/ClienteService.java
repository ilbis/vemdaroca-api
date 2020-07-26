package com.vemdaroca.vemdarocaapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.repository.ClienteRepository;

@Component(value = "clienteService")
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public List<Cliente> getAllClientes() {
		return clienteRepository.findAllStatusActive();
	}

	public Cliente createCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente deleteCliente(Long id) {
		Cliente cl1 = new Cliente();
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (null != cliente) {
			cl1 = cliente.get();
			cl1.setStatus('I');
			return clienteRepository.save(cl1);
		}
		return null;
	}
}
