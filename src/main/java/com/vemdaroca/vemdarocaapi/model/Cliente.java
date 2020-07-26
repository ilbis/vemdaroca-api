package com.vemdaroca.vemdarocaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME", nullable = false, length = 100)
	private String nome;

	@Column(name = "TEL", nullable = false, length = 13)
	private String tel;

	@Column(name = "RUA", nullable = false, length = 100)
	private String rua;

	@Column(name = "NUMERO", nullable = false, length = 10)
	private String numero;

	@Column(name = "BLOCOAP", nullable = true, length = 10)
	private String blocoAp;

	@Column(name = "COMPLEMENTO", nullable = true, length = 50)
	private String complemento;

	@Column(name = "UF", nullable = false, length = 2)
	private String uf;

	@Column(name = "CEP", nullable = false, length = 9)
	private String cep;

	@Column(name = "BAIRRO", nullable = false, length = 30)
	private String bairro;

	@Column(name = "REFERENCIA", nullable = true, length = 50)
	private String referencia;

	@Column(name = "EMAIL", nullable = false, length = 50)
	private String email;

	@Column(name = "STATUS", nullable = false)
	private char status;

	@Column(name = "USERNAME", nullable = false, length = 20)
	private String username;

	@Column(name = "PASSWORD", nullable = false, length = 100)
	private String password;

	@Column(name = "SALT", nullable = false, length = 100)
	private String salt;

	public Cliente() {
	}

	public Cliente(Long id, String nome, String tel, String rua, String numero, String blocoAp, String complemento,
			String uf, String cep, String bairro, String referencia, String email, char status, String username,
			String password, String salt) {
		this.id = id;
		this.nome = nome;
		this.tel = tel;
		this.rua = rua;
		this.numero = numero;
		this.blocoAp = blocoAp;
		this.complemento = complemento;
		this.uf = uf;
		this.cep = cep;
		this.bairro = bairro;
		this.referencia = referencia;
		this.email = email;
		this.status = status;
		this.username = username;
		this.password = password;
		this.salt = salt;
	}

}
