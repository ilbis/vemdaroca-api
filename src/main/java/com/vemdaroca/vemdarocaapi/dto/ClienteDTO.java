package com.vemdaroca.vemdarocaapi.dto;

import com.vemdaroca.vemdarocaapi.model.Cliente;
import com.vemdaroca.vemdarocaapi.model.Role;

import lombok.Getter;

@Getter
public class ClienteDTO {

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
	private String username;
	private String password;

    public Cliente toObject(){
    	return new Cliente(null, nome, tel, rua, numero, complemento, cidade, uf, cep, bairro, email, 'I', username, password, "", null);
    }
}
