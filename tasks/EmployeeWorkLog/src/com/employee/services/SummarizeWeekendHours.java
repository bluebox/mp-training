package com.employee.services;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
	    	
	    	List<String> headers = Arrays.asList("Week day", "Total hours worked");
	    	
	    	ExcelWriter excelWriter  = new ExcelWriter();
	    	Workbook workbook = excelWriter.createWorkBook(headers);
	    	Sheet sheet = workbook.getSheetAt(0);
	        
	        
	        AtomicInteger rowIndex = new AtomicInteger(1);
	        for (Map.Entry<DayOfWeek, Double> entry : weekendHours.entrySet()) {
	            Row row = sheet.createRow(rowIndex.getAndIncrement());
	            if(entry.getKey().getValue() == 6) {
	            	
	            	row.createCell(0).setCellValue("Saturday");
	            }
	            else {

	            	row.createCell(0).setCellValue("Sunday");
	            }
	            row.createCell(1).setCellValue(entry.getValue());
	        }

	        excelWriter.writeToExcel(workbook, filePath);
	        workbook.close();
	    }

}