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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
    @ManyToOne
    @JoinColumn(name="CLIENTE_ID", nullable=false)
    private Cliente cliente;

	public Pedido() {
	}

	public Pedido(Long id, Instant data, Float total, Cliente cliente) {
		this.id = id;
		this.data = data;
		this.total = total;
		this.cliente = cliente;
	}
}
