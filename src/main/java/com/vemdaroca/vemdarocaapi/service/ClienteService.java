package com.vemdaroca.vemdarocaapi.service;

import java.util.Arrays;
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
		return clienteRepository.findAll();
	}

	public Cliente createCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

//	public Cliente deleteCliente(Long id) {
//		Optional<Cliente> cliente = clienteRepository.findById(id);
//		cliente.setStatus('I');
//		return clienteRepository.saveAll(Arrays.asList(cliente));
//	}
}
