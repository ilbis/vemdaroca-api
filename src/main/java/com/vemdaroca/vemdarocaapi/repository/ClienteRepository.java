package com.vemdaroca.vemdarocaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vemdaroca.vemdarocaapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("SELECT e FROM Cliente e WHERE e.status= 'A'")
	List<Cliente> findAllStatusActive();
}
