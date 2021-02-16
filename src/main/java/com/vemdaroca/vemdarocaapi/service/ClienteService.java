package com.vemdaroca.vemdarocaapi.service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vemdaroca.vemdarocaapi.config.ConfigConstants;
import com.vemdaroca.vemdarocaapi.dto.ClienteResponseDTO;
import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.repository.ClienteRepository;
import com.vemdaroca.vemdarocaapi.security.PasswordUtils;

@Component(value = "clienteService")
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public List<ClienteResponseDTO> getAllActive() {
		return clienteRepository.findAllStatusActive().stream().map(c -> ClienteResponseDTO.toDTO(c))
				.collect(Collectors.toList());
	}

	public List<ClienteResponseDTO> getAll() {
		return clienteRepository.findAll().stream().map(c -> ClienteResponseDTO.toDTO(c)).collect(Collectors.toList());
	}

	public Cliente getById(Long id) {
		return clienteRepository.findById(id).get();
	}

	public List<ClienteResponseDTO> getByName(String nome) {
		return clienteRepository.findByName(nome).stream().map(c -> ClienteResponseDTO.toDTO(c))
				.collect(Collectors.toList());
	}

	public ClienteResponseDTO getByUserName(String username) {
		return ClienteResponseDTO.toDTO(clienteRepository.findByUserName(username).get());
	}

	public Cliente getByEmail(String email) {
		return clienteRepository.findByEmail(email).get();
	}

	public ClienteResponseDTO create(Cliente cliente) {
		byte[] decodedBytes = Base64.getDecoder().decode(cliente.getPassword());
		String passwordNew = new String(decodedBytes);
		cliente.setPassword(PasswordUtils.generateSecurePassword(passwordNew, ConfigConstants.SALT));
		return ClienteResponseDTO.toDTO(clienteRepository.save(cliente));
	}

	public ClienteResponseDTO delete(Long id) {
		Cliente entity = clienteRepository.findById(id).get();
		entity.setStatus('I');
		return ClienteResponseDTO.toDTO(clienteRepository.save(entity));
	}

	public ClienteResponseDTO update(Long id, Cliente cliente) {
		byte[] decodedBytes = Base64.getDecoder().decode(cliente.getPassword());
		String passwordNew = new String(decodedBytes);
		cliente.setPassword(PasswordUtils.generateSecurePassword(passwordNew, ConfigConstants.SALT));
		cliente.setStatus('A');
		Cliente entity = clienteRepository.findById(id).get();

		updateData(entity, cliente);
		return ClienteResponseDTO.toDTO(clienteRepository.save(entity));
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
