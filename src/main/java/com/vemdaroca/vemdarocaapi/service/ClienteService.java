package com.vemdaroca.vemdarocaapi.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vemdaroca.vemdarocaapi.config.ConfigConstants;
import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.repository.ClienteRepository;
import com.vemdaroca.vemdarocaapi.security.PasswordUtils;

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

	public Cliente getByUserName(String username) {
		return clienteRepository.findByUserName(username).get();
	}

	public Cliente create(Cliente cliente) {
		byte[] decodedBytes = Base64.getDecoder().decode(cliente.getPassword());
		String passwordNew = new String(decodedBytes);
		cliente.setPassword(PasswordUtils.generateSecurePassword(passwordNew, ConfigConstants.SALT));
		return clienteRepository.save(cliente);
	}

	public Cliente delete(Long id) {
		Cliente entity = clienteRepository.findById(id).get();
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
		entity.setComplemento(cliente.getComplemento());
		entity.setCidade(cliente.getCidade());
		entity.setUf(cliente.getUf());
		entity.setCep(cliente.getCep());
		entity.setBairro(cliente.getBairro());
		entity.setEmail(cliente.getEmail());
		entity.setStatus(cliente.getStatus());
		entity.setUsername(cliente.getUsername());
		entity.setPassword(cliente.getPassword());
		entity.setValidatorCode(cliente.getValidatorCode());
	}
}
