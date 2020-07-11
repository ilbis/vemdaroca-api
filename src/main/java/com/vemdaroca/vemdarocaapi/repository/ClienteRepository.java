package com.vemdaroca.vemdarocaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vemdaroca.vemdarocaapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
