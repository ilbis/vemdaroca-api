package com.vemdaroca.vemdarocaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vemdaroca.vemdarocaapi.model.ItemPedido;
import com.vemdaroca.vemdarocaapi.service.ItemPedidoService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/itempedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@GetMapping(value = "/all")
	@ApiOperation(value = "Retorna todos items de pedido")
	public ResponseEntity<List<ItemPedido>> getAll() {
		return ResponseEntity.ok().body(itemPedidoService.getAll());
	}

}
