package com.employee.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.domain.EmployeeWorkLog;

public class UrgentCriticalLogs {
	
	 public void writeUrgentCriticalLogs(List<EmployeeWorkLog> logs, String filePath) throws IOException {
	    	
	    	logs = logs.stream()
	                .filter(log -> log.getRemarks().toLowerCase().contains("urgent") || log.getRemarks().toLowerCase().contains("critical"))
	                .sorted(Comparator.comparing(EmployeeWorkLog::getName))
	                .collect(Collectors.toList());
	    	
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Urgent Logs");

	        String[] headers = {"Emp ID", "Name", "Dept", "Project", "Date", "Task", "Hours", "Remarks"};
	        Row header = sheet.createRow(0);
	        for (int i = 0; i < headers.length; i++) {
	        	
	        	header.createCell(i).setCellValue(headers[i]);
	        }

	        int rowIndex = 1;
	        for (EmployeeWorkLog log : logs) {
	            Row row = sheet.createRow(rowIndex++);
	            row.createCell(0).setCellValue(log.getEmployeeId());
	            row.createCell(1).setCellValue(log.getName());
	            row.createCell(2).setCellValue(log.getDepartment());
	            row.createCell(3).setCellValue(log.getProjectId());
	            row.createCell(4).setCellValue(log.getDate().toString());
	            row.createCell(5).setCellValue(log.getTaskCategory());
	            row.createCell(6).setCellValue(log.getHoursWorked());
	            row.createCell(7).setCellValue(log.getRemarks());
	        }

	        try (FileOutputStream fos = new FileOutputStream(filePath)) {
	            workbook.write(fos);
	        }
	        catch(IOException e) {
	        	e.printStackTrace();
	        }
	        workbook.close();
	    }
}
