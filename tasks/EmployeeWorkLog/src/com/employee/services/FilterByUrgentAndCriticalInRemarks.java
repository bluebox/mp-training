package com.employee.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.employee.domain.EmployeeWorkLog;

public class FilterByUrgentAndCriticalInRemarks {

	public void writeUrgentCriticalLogs(List<EmployeeWorkLog> logs, String filePath) throws IOException {

		logs = logs.stream()
				.filter(log -> log.getRemarks().toLowerCase().contains("urgent")
						|| log.getRemarks().toLowerCase().contains("critical"))
				.sorted(Comparator.comparing(EmployeeWorkLog::getName)).collect(Collectors.toList());


		String[] columnNames = { "Emp ID", "Name", "Dept", "Project", "Date", "Task", "Hours", "Remarks" };
		List<String> headers = Arrays.asList(columnNames);
		
		ExcelWriter excelWriter  = new ExcelWriter();
		Workbook workbook = excelWriter.createWorkBook(headers);
    	Sheet sheet = workbook.getSheetAt(0);
        
		AtomicInteger rowIndex = new AtomicInteger(1);
		for (EmployeeWorkLog log : logs) {
			
			Row row = sheet.createRow(rowIndex.getAndIncrement());
			row.createCell(0).setCellValue(log.getEmployeeId());
			row.createCell(1).setCellValue(log.getName());
			row.createCell(2).setCellValue(log.getDepartment());
			row.createCell(3).setCellValue(log.getProjectId());
			row.createCell(4).setCellValue(log.getDate().toString());
			row.createCell(5).setCellValue(log.getTaskCategory());
			row.createCell(6).setCellValue(log.getHoursWorked());
			row.createCell(7).setCellValue(log.getRemarks());
		}

		excelWriter.writeToExcel(workbook, filePath);
		workbook.close();
	}
}