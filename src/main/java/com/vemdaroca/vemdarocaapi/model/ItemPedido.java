package com.vemdaroca.vemdarocaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMPEDIDO")
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	//adicionar  nome, tipo, unidademedida 
	//
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne()
	@JoinColumn(name = "PRODUTO_ID")
	private Produto produto;

	@OneToOne()
	@JoinColumn(name = "PEDIDO_ID")
	private Pedido pedido;

	@Column(name = "QTD", nullable = false)
	private Float qtd;

	@Column(name = "VALOR", nullable = false)
	private Float valor;

	public ItemPedido() {
	}

	public ItemPedido(Long id, Produto produto, Pedido pedido, Float qtd, Float valor) {
		this.id = id;
		this.produto = produto;
		this.pedido = pedido;
		this.qtd = qtd;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Float getQtd() {
		return qtd;
	}

	public void setQtd(Float qtd) {
		this.qtd = qtd;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

}
