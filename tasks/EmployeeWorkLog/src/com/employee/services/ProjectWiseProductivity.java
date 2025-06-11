package com.employee.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.employee.domain.EmployeeWorkLog;

public class ProjectWiseProductivity {

	public void writeProjectProductivity(List<EmployeeWorkLog> logs, String filePath) throws IOException {
    	
    	
    	Map<String, Map<String, Double>> data = new HashMap<>();
        Map<String, List<EmployeeWorkLog>> groupedByProject = logs.stream()
        				.collect(Collectors
        				.groupingBy(EmployeeWorkLog::getProjectId));

        for (Map.Entry<String, List<EmployeeWorkLog>> entry : groupedByProject.entrySet()) {
            String projectId = entry.getKey();
            List<EmployeeWorkLog> projectLogs = entry.getValue();
            
            double total = projectLogs.stream()
            		.mapToDouble(EmployeeWorkLog::getHoursWorked).sum();
            long uniqueEmployeesCount = projectLogs.stream()
            		.map(EmployeeWorkLog::getEmployeeId).distinct().count();
            double avg = uniqueEmployeesCount == 0 ? 0.0 : total / uniqueEmployeesCount;

            Map<String, Double> metrics = new HashMap<>();
            metrics.put("Total", total);
            metrics.put("Average", avg);
            data.put(projectId, metrics);
        }
        
        List<String> headers = Arrays.asList("Project ID","Total Hours","Average Hours per Employee");
    	
        ExcelWriter excelWriter  = new ExcelWriter();
    	Workbook workbook = excelWriter.createWorkBook(headers);
    	Sheet sheet = workbook.getSheetAt(0);

        AtomicInteger rowIndex = new AtomicInteger(1);
        for (Map.Entry<String, Map<String, Double>> entry : data.entrySet()) {
            Row row = sheet.createRow(rowIndex.getAndIncrement());
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue().get("Total"));
            row.createCell(2).setCellValue(entry.getValue().get("Average"));
        }

        excelWriter.writeToExcel(workbook, filePath);
        workbook.close();
    }
}