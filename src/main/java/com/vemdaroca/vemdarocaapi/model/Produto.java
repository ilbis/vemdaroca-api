package com.vemdaroca.vemdarocaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private float valor;

	@Enumerated(EnumType.STRING)
	private UnidMedida unidMedida;

	public Produto() {
	}

	public Produto(Long id, String nome, String tipo, float valor, UnidMedida unidMedida) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
		this.unidMedida = unidMedida;
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

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public UnidMedida getUnidMedida() {
		return unidMedida;
	}

	public void setUnidMedida(UnidMedida unidMedida) {
		this.unidMedida = unidMedida;
	}

}
