package com.employee.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.domain.EmployeeWorkLog;

public class DaysWithLessthanTwoHours {
	
	
	public void writeLowHourLogs(List<EmployeeWorkLog> logs, String filePath) throws IOException {
    	
    	Map<String, List<LocalDate>> data = logs.stream()
                .filter(log -> log.getHoursWorked() < 2.0)
                .collect(Collectors.groupingBy(
                        EmployeeWorkLog::getEmployeeId,
                        Collectors.mapping(EmployeeWorkLog::getDate, Collectors.toList())
                ));
    	
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Low Hour Days");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Employee ID");
        header.createCell(1).setCellValue("Dates with <2 Hours");

        int rowIndex = 1;
        for (Map.Entry<String, List<LocalDate>> entry : data.entrySet()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue().toString());
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
