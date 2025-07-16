package com.demo.employeeelog;


	

	import org.apache.poi.ss.usermodel.*;
	import java.io.FileInputStream;
	import java.time.LocalDate;
	import java.util.ArrayList;
	import java.util.List;

	public class logs {
	    public static List<EmployeeWorkLog> readExcel(String filePath) throws Exception {
	        List<EmployeeWorkLog> logs = new ArrayList<>();
	        FileInputStream fis = new FileInputStream(filePath);
	        Workbook workbook = WorkbookFactory.create(fis);
	        Sheet sheet = workbook.getSheetAt(0);

	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) continue; 

	            String employeeId = row.getCell(0).getStringCellValue();
	            String name = row.getCell(1).getStringCellValue();
	            String department = row.getCell(2).getStringCellValue();
	            String projectId = row.getCell(3).getStringCellValue();
	            LocalDate date = row.getCell(4).getLocalDateTimeCellValue().toLocalDate();
	            String taskCategory = row.getCell(5).getStringCellValue();
	            double hoursWorked = row.getCell(6).getNumericCellValue();
	            String remarks = row.getCell(7).getStringCellValue();

	            logs.add(new EmployeeWorkLog(employeeId, name, department,
	                    date, hoursWorked, remarks));
	        }

	        workbook.close();
	        fis.close();

	        return logs;
	    }
	}


