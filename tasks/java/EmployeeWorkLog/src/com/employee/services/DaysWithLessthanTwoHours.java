package com.employee.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.employee.domain.EmployeeWorkLog;

public class DaysWithLessthanTwoHours {
	
	
	public void writeLowHourLogs(List<EmployeeWorkLog> logs, String filePath) throws IOException {
    	
    	Map<String, List<LocalDate>> data = logs.stream()
                .filter(log -> log.getHoursWorked() < 2.0)
                .collect(Collectors.groupingBy(
                        EmployeeWorkLog::getEmployeeId,
                        Collectors.mapping(EmployeeWorkLog::getDate, Collectors.toList())
                ));
    	
    	List<String> headers = Arrays.asList("Employee ID","Dates with <2 Hours");
      	
        ExcelWriter excelWriter  = new ExcelWriter();
      	Workbook workbook = excelWriter.createWorkBook(headers);
      	Sheet sheet = workbook.getSheetAt(0);

        AtomicInteger rowIndex = new AtomicInteger(1);
        for (Map.Entry<String, List<LocalDate>> entry : data.entrySet()) {
            Row row = sheet.createRow(rowIndex.getAndIncrement());
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue().toString());
        }

        excelWriter.writeToExcel(workbook, filePath);
        workbook.close();
    }
}