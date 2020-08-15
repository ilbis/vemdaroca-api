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

	public List<Cliente> getAllActive() {
		return clienteRepository.findAllStatusActive();
	}

	public List<Cliente> getAll() {
		return clienteRepository.findAll();
	}

	public Cliente getById(Long id) {
		return clienteRepository.findById(id).get();
	}

	public List<Cliente> getByName(String nome) {
		return clienteRepository.findByName(nome);
	}

	public Cliente create(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente delete(Long id) {
		Cliente entity = new Cliente();
		Optional<Cliente> cliente = clienteRepository.findById(id);
		entity = cliente.get();
		entity.setStatus('I');
		return clienteRepository.save(entity);
	}

	public Cliente update(Long id, Cliente cliente) {
		Cliente entity = clienteRepository.findById(id).get();
		updateData(entity, cliente);
		return clienteRepository.save(entity);
	}

	private void updateData(Cliente entity, Cliente cliente) {
		entity.setNome(cliente.getNome());
		entity.setTel(cliente.getTel());
		entity.setRua(cliente.getRua());
		entity.setNumero(cliente.getNumero());
		entity.setBlocoAp(cliente.getBlocoAp());
		entity.setComplemento(cliente.getComplemento());
		entity.setUf(cliente.getUf());
		entity.setCep(cliente.getCep());
		entity.setBairro(cliente.getBairro());
		entity.setReferencia(cliente.getReferencia());
		entity.setEmail(cliente.getEmail());
		entity.setStatus(cliente.getStatus());
		entity.setUsername(cliente.getUsername());
		entity.setPassword(cliente.getPassword());
		entity.setSalt(cliente.getSalt());
	}
}
