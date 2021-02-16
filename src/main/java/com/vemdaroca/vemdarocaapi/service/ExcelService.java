package com.vemdaroca.vemdarocaapi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.vemdaroca.vemdarocaapi.dto.ClienteResponseDTO;
import com.vemdaroca.vemdarocaapi.model.ItemPedido;

@Service
public class ExcelService {

	private static int LINHA_INICIAL_PRODUTO = 15;
	private static int LINHA_FINAL_PRODUTO = 112;
	private static int COLUNA_PRODUTO = 1;
	private static int COLUNA_VALOR_UNITARIO = 2;
	private static int COLUNA_QTD = 8;
	private static int COLUNA_TIPO = 9;
	private static int COLUNA_TOTAL = 10;

	public void AddRegistroExcel(ClienteResponseDTO cliente, List<ItemPedido> itensPedido, String excelFilePath) {

		try {
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = WorkbookFactory.create(inputStream);

			Sheet sheet = workbook.getSheetAt(0);

			Object[] bookData = { cliente.getId().toString(),
					cliente.getBairro() + " - " + cliente.getCidade() + " - " + cliente.getUf(), cliente.getNome(),
					cliente.getRua() + ", " + cliente.getNumero(), "", cliente.getTel() };

			int line = 3;
			for (Object field : bookData) {
				Row row = sheet.getRow(line++); // seta linha

				Cell cell = row.getCell(8); // seta coluna
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}

			itensPedido.forEach(item -> {
				for (int i = LINHA_INICIAL_PRODUTO; i <= LINHA_FINAL_PRODUTO; i++) {
					Row row = sheet.getRow(i);
					Cell cell = row.getCell(COLUNA_PRODUTO);

					if (cell.toString().trim().toUpperCase()
							.equals(item.getProduto().getNome().toString().trim().toUpperCase())) {
						Cell cellUnitario = row.getCell(COLUNA_VALOR_UNITARIO);
						Cell cellQtd = row.getCell(COLUNA_QTD);
						Cell cellTipo = row.getCell(COLUNA_TIPO);
						Cell cellCusto = row.getCell(COLUNA_TOTAL);
						cellUnitario.setCellValue(item.getValor());
						cellQtd.setCellValue(item.getQtd());
						cellTipo.setCellValue(item.getProduto().getTipo().toString());
						cellCusto.setCellValue(item.getSubTotal());
						break;
					}
				}
			});

			inputStream.close();

			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();

		} catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
			ex.printStackTrace();
		}
	}

//	int rowCount = sheet.getLastRowNum();
//	int columnCountSize = sheet.getDefaultColumnWidth();

//	for (Object[] aBook : bookData) {

//		Row row = sheet.createRow(++rowCount);
//		int columnCount = 0;

//		Cell cell = row.createCell(columnCount);
//		cell.setCellValue(rowCount);
//
//		for (Object field : aBook) {
//			cell = row.createCell(++columnCount);
//			if (field instanceof String) {
//				cell.setCellValue((String) field);
//			} else if (field instanceof Integer) {
//				cell.setCellValue((Integer) field);
//			}
//	}

//	int line = 3;
//	for (Object field : bookData) {
//		Row row = sheet.createRow(line++); // seta linha
//		Row row = sheet.getRow(line++); // seta linha
//
//		Cell cell = row.getCell(8); // seta coluna
//		if (field instanceof String) {
//			cell.setCellValue((String) field);
//		} else if (field instanceof Integer) {
//			cell.setCellValue((Integer) field);
//		}
//	}

//}
}
