package com.employee.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.domain.EmployeeWorkLog;

public class ProjectWiseProductivity {

	public void writeProjectProductivity(List<EmployeeWorkLog> logs, String filePath) throws IOException {
    	
    	
    	Map<String, Map<String, Double>> data = new HashMap<>();
        Map<String, List<EmployeeWorkLog>> groupedByProjectId = logs.stream()
        				.collect(Collectors
        				.groupingBy(EmployeeWorkLog::getProjectId));

        for (Map.Entry<String, List<EmployeeWorkLog>> entry : groupedByProjectId.entrySet()) {
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
    	
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Project Productivity");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Project ID");
        header.createCell(1).setCellValue("Total Hours");
        header.createCell(2).setCellValue("Average Hours per Employee");

        int rowIndex = 1;
        for (Map.Entry<String, Map<String, Double>> entry : data.entrySet()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue().get("Total"));
            row.createCell(2).setCellValue(entry.getValue().get("Average"));
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
        workbook.close();
    }
}
