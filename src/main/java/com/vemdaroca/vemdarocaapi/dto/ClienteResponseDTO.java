package com.vemdaroca.vemdarocaapi.dto;

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.model.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ClienteResponseDTO {

	private Long id;
	private String nome;
	private String tel;
	private String rua;
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;
	private String cep;
	private String bairro;
	private String email;
	private char status;
	private String username;
	private Role role;

	public static ClienteResponseDTO toDTO(Cliente cliente) {
		return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getTel(), cliente.getRua(),
				cliente.getNumero(), cliente.getComplemento(),cliente.getCidade(),cliente.getUf(),cliente.getCep(),cliente.getBairro(),
				cliente.getEmail(),cliente.getStatus(),cliente.getUsername(),cliente.getRole());
	}
}
