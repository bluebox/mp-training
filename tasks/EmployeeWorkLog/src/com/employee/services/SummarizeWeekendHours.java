package com.employee.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.domain.EmployeeWorkLog;

public class SummarizeWeekendHours {
	
	 public void writeWeekendSummary(List<EmployeeWorkLog> logs, String filePath) throws IOException {
	    	
	    	Map<DayOfWeek, Double> weekendHours = logs.stream()
	    		    .filter(log -> {
	    		        DayOfWeek day = log.getDate().getDayOfWeek();
	    		        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
	    		    })
	    		    .collect(Collectors.groupingBy(
	    		        log -> log.getDate().getDayOfWeek(),
	    		        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
	    		    ));
	    	
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Weekend Summary");
	        Row header = sheet.createRow(0);
	        header.createCell(0).setCellValue("Week day");
	        header.createCell(1).setCellValue("Total Hours");
	        
	        int rowIndex = 1;
	        for (Map.Entry<DayOfWeek, Double> entry : weekendHours.entrySet()) {
	            Row row = sheet.createRow(rowIndex++);
	            if(entry.getKey().getValue() == 6) {
	            	
	            	row.createCell(0).setCellValue("Saturday");
	            }
	            else {

	            	row.createCell(0).setCellValue("Sunday");
	            }
	            row.createCell(1).setCellValue(entry.getValue());
	        }

	        try (FileOutputStream fos = new FileOutputStream(filePath)) {
	            workbook.write(fos);
	        }
	        workbook.close();
	    }

}
