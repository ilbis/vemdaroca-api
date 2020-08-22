package com.vemdaroca.vemdarocaapi.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME", nullable = false, unique = true, length = 100)
	private String nome;

	@Column(name = "TIPO", nullable = false, length = 10)
	private String tipo;

	@Column(name = "VALOR", nullable = false)
	private Double valor;

	@Enumerated(EnumType.STRING)
	private UnidMedida unidMedida;

	@Column(name = "STATUS", nullable = false)
	private char status;

	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> items = new HashSet<>();

	public Produto() {
	}

	public Produto(Long id, String nome, String tipo, Double valor, UnidMedida unidMedida, char status) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
		this.unidMedida = unidMedida;
		this.status = status;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public UnidMedida getUnidMedida() {
		return unidMedida;
	}

	public void setUnidMedida(UnidMedida unidMedida) {
		this.unidMedida = unidMedida;
	}

	@JsonIgnore
	public Set<Pedido> getPedidos() {
		Set<Pedido> set = new HashSet<>();
		for (ItemPedido x : items) {
			set.add(x.getPedido());
		}
		return set;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
