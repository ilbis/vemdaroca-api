package com.vemdaroca.vemdarocaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vemdaroca.vemdarocaapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT e FROM Produto e WHERE e.status= 'A'")
	List<Produto> findAllStatusActive();

	@Query("SELECT e FROM Produto e WHERE e.nome LIKE :nome%")
	List<Produto> findByName(@Param("nome") String nome);

	@Query("SELECT e FROM Produto e WHERE e.tipo LIKE :tipo%")
	List<Produto> findByTipo(@Param("tipo") String tipo);
}
