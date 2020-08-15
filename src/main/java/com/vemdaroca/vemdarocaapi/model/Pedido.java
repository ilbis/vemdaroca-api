package com.vemdaroca.vemdarocaapi.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDO")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DATA", nullable = false)
	private Instant data;

	@Column(name = "TOTAL", nullable = false)
	private Float total;

	@Column(name = "STATUS", nullable = false)
	private char status;

	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID", nullable = false)
	private Cliente cliente;

	public Pedido() {
	}

	public Pedido(Long id, Instant data, Float total, char status, Cliente cliente) {
		this.id = id;
		this.data = data;
		this.total = total;
		this.status = status;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
