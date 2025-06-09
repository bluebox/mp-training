package com.employee.services;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.domain.EmployeeWorkLog;

public class ExcelReader {
	
public List<EmployeeWorkLog> readExcel(String filePath) throws Exception {
        
    	List<EmployeeWorkLog> logs = new ArrayList<>();
    	
        InputStream fis = Files.newInputStream(Paths.get(filePath));
        
            try (Workbook workbook = new XSSFWorkbook(fis)) {
				Sheet sheet = workbook.getSheetAt(0);
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					
				    Row row = sheet.getRow(i);
				    if (row == null) {
				    	continue;
				    }
				    	
				    Cell dateCell = row.getCell(4);
				    LocalDate date;

				    if (dateCell == null) {
				        continue;
				    } 
				    else if (dateCell.getCellType() == CellType.NUMERIC) {
				        date = dateCell.getLocalDateTimeCellValue().toLocalDate();
				    } 
				    else 
				    {
				        String dateStr = dateCell.getStringCellValue().trim();
				        date = LocalDate.parse(dateStr);
				    }

				    EmployeeWorkLog log = new EmployeeWorkLog(
				            row.getCell(0).getStringCellValue(),
				            row.getCell(1).getStringCellValue(),
				            row.getCell(2).getStringCellValue(),
				            row.getCell(3).getStringCellValue(),
				            date,
				            row.getCell(5).getStringCellValue(),
				            row.getCell(6).getNumericCellValue(),
				            row.getCell(7).getStringCellValue()
				    );
				    logs.add(log);
				}
			}
            
            return logs;
    }
}
