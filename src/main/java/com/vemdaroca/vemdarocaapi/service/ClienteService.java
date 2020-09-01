package com.vemdaroca.vemdarocaapi.service;

import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		Cliente clienteDTO = new Cliente();
		clienteDTO = cliente;
		String passwordDecode = new String(Base64.decodeBase64(cliente.getPassword()));
		String salt = PasswordUtils.getSalt(30);
		String securePassword = PasswordUtils.generateSecurePassword(passwordDecode, salt);
		clienteDTO.setPassword(securePassword);
		clienteDTO.setSalt(salt);

		return clienteRepository.save(clienteDTO);
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
