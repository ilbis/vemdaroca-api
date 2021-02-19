package com.vemdaroca.vemdarocaapi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.vemdaroca.vemdarocaapi.model.ItemPedido;
import com.vemdaroca.vemdarocaapi.model.Pedido;
import com.vemdaroca.vemdarocaapi.model.Produto;

@Service
public class ExcelService {

	private static int LINHA_INICIAL_PRODUTO = 15;
	private static int LINHA_FINAL_PRODUTO = 112;
	private static int COLUNA_PRODUTO = 1;
	private static int COLUNA_VALOR_UNITARIO = 2;
	private static int COLUNA_QTD = 8;
	private static int COLUNA_TIPO = 9;
	private static int COLUNA_TOTAL = 10;

	public void AddRegistroExcel(List<Produto> produtos, List<Pedido> pedidos, String excelFilePath) {

		try {
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = WorkbookFactory.create(inputStream);

			Sheet sheet = workbook.getSheetAt(0);

			// mapa idProduto - linhaProduto
			Map<Long, Integer> mapaProduto = new HashMap<Long, Integer>();

			int linhaProduto = 14; // linha inicial produtos

			for (Produto produto : produtos) {
				mapaProduto.put(produto.getId(), linhaProduto);

				Row row = sheet.getRow(linhaProduto); // seta linha

				Cell nome = row.getCell(1); // seta coluna
				Cell valor = row.getCell(2);
				Cell tipo = row.getCell(3);
				Cell unidMedida = row.getCell(4);

				nome.setCellValue(produto.getNome());
				valor.setCellValue(produto.getValor());
				tipo.setCellValue(produto.getTipo().toString());
				unidMedida.setCellValue(produto.getUnidMedida().toString());

				linhaProduto++;
			}

			int colunaPedido = 6;

			for (Pedido pedido : pedidos) {
				String[] valores = { pedido.getId().toString(), pedido.getCliente().getId().toString(),
						pedido.getDataFormatada(), pedido.getCliente().getNome(), pedido.getCliente().getUsername(),
						pedido.getCliente().getEmail(),
						pedido.getCliente().getRua() + ", " + pedido.getCliente().getNumero() + " - "
								+ pedido.getCliente().getBairro() + " - " + pedido.getCliente().getCidade() + " - "
								+ pedido.getCliente().getCep(),
						pedido.getCliente().getComplemento(), pedido.getCliente().getTel() };

				// Seta dados do cliente
				for (int linha = 1; linha < 10; linha++) {
					Row row = sheet.getRow(linha); // seta linha
					Cell cell = row.getCell(colunaPedido);
					cell.setCellValue(valores[linha - 1]);
				}

				for (ItemPedido item : pedido.getItems()) {
					Row row = sheet.getRow(mapaProduto.get(item.getProduto().getId())); // seta linha
					Cell qtd = row.getCell(colunaPedido);
					Cell tipo = row.getCell(colunaPedido + 1);
					Cell total = row.getCell(colunaPedido + 2);

					qtd.setCellValue(item.getQtd());
					tipo.setCellValue(item.getProduto().getTipo().toString());
					total.setCellValue(item.getSubTotal());
				}

				Row row = sheet.getRow(linhaProduto); // seta linha
				Cell nome = row.getCell(colunaPedido + 1); // seta coluna
				Cell total = row.getCell(colunaPedido + 2); // seta coluna
				
				nome.setCellValue("TOTAL");
				total.setCellValue(pedido.getTotal());

				colunaPedido += 4;
			}

			inputStream.close();

			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();

		} catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
			ex.printStackTrace();
		}
	}

}