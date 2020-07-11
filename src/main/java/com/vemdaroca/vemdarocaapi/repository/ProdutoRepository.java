package com.vemdaroca.vemdarocaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vemdaroca.vemdarocaapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
