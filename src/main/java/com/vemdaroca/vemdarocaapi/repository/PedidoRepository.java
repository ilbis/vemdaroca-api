package com.vemdaroca.vemdarocaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vemdaroca.vemdarocaapi.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("SELECT e FROM Pedido e WHERE e.status= 'A'")
	List<Pedido> findAllStatusActive();

	@Query("SELECT e FROM Pedido e WHERE e.cliente.id= :id")
	List<Pedido> findAllByCliente(Long id);
}
