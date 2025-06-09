package com.employee.services;

import com.employee.domain.EmployeeWorkLog;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeAnalytics {

    public List<EmployeeWorkLog> readExcel(String filePath) throws Exception {
        
    	List<EmployeeWorkLog> logs = new ArrayList<>();
        try (InputStream fis = Files.newInputStream(Paths.get(filePath));
            Workbook workbook = new XSSFWorkbook(fis)) {
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

    
    public void writeUrgentLogs(List<EmployeeWorkLog> logs, String filePath) throws IOException {
    	
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

    public void writeTagCounts(List<EmployeeWorkLog> logs, String filePath) throws IOException {
    	
    	Map<String, Long> tagCounts = logs.stream()
                .map(EmployeeWorkLog::getRemarks)
                .filter(Objects::nonNull)
                .map(String::toLowerCase)
                .map(String::trim)
                .filter(remark -> !remark.isEmpty())
                .collect(Collectors.groupingBy(remark -> remark, Collectors.counting()));
    	
        	Workbook workbook = new XSSFWorkbook();
          
            Sheet sheet = workbook.createSheet("Tags Summary");

            Row header = sheet.createRow(0);
            Cell tagHeader = header.createCell(0);
            tagHeader.setCellValue("Tag");
          
            Cell countHeader = header.createCell(1);
            countHeader.setCellValue("Count");


            int rowIndex = 1;
            for (Map.Entry<String, Long> entry : tagCounts.entrySet()) {
                String tag = entry.getKey();
                Long count = entry.getValue();

                if (tag == null || count == null) {
                	continue;
                }

                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(tag);
                row.createCell(1).setCellValue(count);
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            catch(IOException e) {
            	e.printStackTrace();
            }
            workbook.close();
    }
    
    

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
        catch(IOException e) {
        	
        	e.printStackTrace();
        }
        workbook.close();
    }

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
