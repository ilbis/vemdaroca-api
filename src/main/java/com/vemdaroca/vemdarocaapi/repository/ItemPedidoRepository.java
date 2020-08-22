package com.vemdaroca.vemdarocaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vemdaroca.vemdarocaapi.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

	@Query("SELECT e FROM ItemPedido e WHERE e.status= 'A'")
	List<ItemPedido> findAllStatusActive();
}
