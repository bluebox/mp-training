package com.service;



import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToExcel {

	public static void writeToExcel(String fileName, List<String> columnTitles, List<List<Object>> rows) {
		try {
			XSSFWorkbook excelFile = new XSSFWorkbook();
			XSSFSheet sheet = excelFile.createSheet("Sheet1");

			XSSFRow titleRow = sheet.createRow(0);
			for (int col = 0; col < columnTitles.size(); col++) {
				XSSFCell cell = titleRow.createCell(col);
				cell.setCellValue(columnTitles.get(col));
			}

			for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
				XSSFRow row = sheet.createRow(rowIndex + 1);
				List<Object> rowValues = rows.get(rowIndex);
				for (int colIndex = 0; colIndex < rowValues.size(); colIndex++) 
				{
					XSSFCell cell = row.createCell(colIndex);
					Object value = rowValues.get(colIndex);
					
					if (value instanceof String) {
						cell.setCellValue((String) value);
					}
					
					else if (value instanceof Double) {
						cell.setCellValue((Double) value);
					} 
					
					else if (value instanceof Integer) {
						cell.setCellValue((Integer) value);
					} 
					
					else if (value instanceof java.util.Date) {
						cell.setCellValue((java.util.Date) value);
						XSSFCellStyle dateFormat = excelFile.createCellStyle();
						XSSFCreationHelper formatHelper = excelFile.getCreationHelper();
						dateFormat.setDataFormat(formatHelper.createDataFormat().getFormat("yyyy-MM-dd"));
						cell.setCellStyle(dateFormat);
					}
				}
			}

			for (int col = 0; col < columnTitles.size(); col++) {
				sheet.autoSizeColumn(col);
			}

			FileOutputStream fileOut = new FileOutputStream("output/" + fileName + ".xlsx");
			excelFile.write(fileOut);
			fileOut.close();
			System.out.println(fileName + " created and data written into it.");
			excelFile.close();

		} catch (IOException e) {
			System.out.println("Error creating Excel file:");
			e.printStackTrace();
		}
	}
}