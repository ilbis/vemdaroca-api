package com.vemdaroca.vemdarocaapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBlocoAp() {
		return blocoAp;
	}

	public void setBlocoAp(String blocoAp) {
		this.blocoAp = blocoAp;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
