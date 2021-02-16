package com.vemdaroca.vemdarocaapi.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vemdaroca.vemdarocaapi.dto.CommandReturnDTO;
import com.vemdaroca.vemdarocaapi.model.DataRelatorio;
import com.vemdaroca.vemdarocaapi.model.Pedido;
import com.vemdaroca.vemdarocaapi.repository.PedidoRepository;
import com.vemdaroca.vemdarocaapi.util.CommandLineUtil;

@Component(value = "pedidoService")
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	private CommandLineUtil commandLineUtil;

	public List<Pedido> getAllActive() {
		return pedidoRepository.findAllStatusActive();
	}

	public List<Pedido> getAll() {
		return pedidoRepository.findAll();
	}

	public Pedido getById(Long id) {
		return pedidoRepository.findById(id).get();
	}

	public List<Pedido> getByIdCliente(Long id) {
		return pedidoRepository.findAllByCliente(id);
	}

	public Pedido create(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public Pedido delete(Long id) {
		Pedido entity = pedidoRepository.findById(id).get();
		entity.setStatus('I');
		return pedidoRepository.save(entity);
	}

	public Pedido update(Long id, Pedido pedido) {
		Pedido entity = pedidoRepository.findById(id).get();
		updateData(entity, pedido);
		return pedidoRepository.save(entity);
	}

	private void updateData(Pedido entity, Pedido pedido) {
		entity.setStatus(pedido.getStatus());
	}

	public File gerarRelatorio(DataRelatorio data) {
		try {
			String command = "curl -fsSL -o /tmp/Tabela.xlsx https://docs.google.com/spreadsheets/d/e/2PACX-1vSWxfvK3Qk0w4Tx9LAboQJa850J-pZK56wR0QbyiYeNnyZBXb169toQKDlSYmDwLdzcnEpbOgiXqxpI/pub?output=xlsx";
			CommandReturnDTO response = commandLineUtil.executeCommandLine("/tmp", command);
			System.out.println(response.getLogError());

		} catch (IOException | InterruptedException e) {
			System.out.println("Erro ao baixar arquivo");
		}
		return new File("/tmp/Tabela.xlsx");
	}

}
