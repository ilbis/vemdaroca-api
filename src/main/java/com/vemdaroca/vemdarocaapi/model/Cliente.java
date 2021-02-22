package com.vemdaroca.vemdarocaapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME", length = 100)
	private String nome;

	@Column(name = "TEL", length = 14)
	private String tel;

	@Column(name = "RUA", length = 100)
	private String rua;

	@Column(name = "NUMERO", length = 10)
	private String numero;

	@Column(name = "COMPLEMENTO", length = 50)
	private String complemento;

	@Column(name = "CIDADE", length = 30)
	private String cidade;

	@Column(name = "UF", length = 2)
	private String uf;

	@Column(name = "CEP", length = 9)
	private String cep;

	@Column(name = "BAIRRO", length = 30)
	private String bairro;

	@Column(name = "EMAIL", length = 50)
	private String email;

	@Column(name = "STATUS")
	private char status;

	@Column(name = "USERNAME", unique = true, length = 20)
	private String username;

	@Column(name = "PASSWORD", length = 50)
	private String password;

	@Column(name = "VALIDATORCODE", length = 15)
	private String validatorCode;

	@Enumerated(EnumType.STRING)
	private Role role;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente() {
	}

	public Cliente(Long id, String nome, String tel, String rua, String numero, String complemento, String cidade,
			String uf, String cep, String bairro, String email, char status, String username, String password,
			String validatorCode, Role role) {
		this.id = id;
		this.nome = nome;
		this.tel = tel;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.bairro = bairro;
		this.email = email;
		this.status = status;
		this.username = username;
		this.password = password;
		this.validatorCode = validatorCode;
		this.role = role;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getValidatorCode() {
		return validatorCode;
	}

	public void setValidatorCode(String validatorCode) {
		this.validatorCode = validatorCode;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> role = new ArrayList<>();
		role.add(new SimpleGrantedAuthority(this.role.toString()));
		return role;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", tel=" + tel + ", rua=" + rua + ", numero=" + numero + ", complemento="
				+ complemento + ", cidade=" + cidade + ", uf=" + uf + ", cep=" + cep + ", bairro=" + bairro + ", email="
				+ email + "]";
	}

}
