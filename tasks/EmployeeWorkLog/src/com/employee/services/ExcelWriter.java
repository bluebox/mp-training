package com.employee.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

	public Workbook createWorkBook(List<String> headers) {
		Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        Row header = sheet.createRow(0);
        
        AtomicInteger index = new AtomicInteger(0);
        
        for(String column : headers) {
        	
        	header.createCell(index.getAndIncrement()).setCellValue(column);
        	
        }
        
        return workbook;
	}
	
	public void writeToExcel(Workbook workbook,String filePath) throws FileNotFoundException, IOException {
		
		try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
        workbook.close();
	}
}