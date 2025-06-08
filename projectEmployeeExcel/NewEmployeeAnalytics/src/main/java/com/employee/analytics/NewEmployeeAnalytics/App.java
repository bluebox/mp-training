package com.employee.analytics.NewEmployeeAnalytics;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    public static class EmployeeWorkLog {
        String employeeId, name, department, projectId, taskCategory, remarks;
        LocalDate date;
        double hoursWorked;

        public EmployeeWorkLog(String employeeId, String name, String department, String projectId,
                             LocalDate date, String taskCategory, double hoursWorked, String remarks) {
            this.employeeId = employeeId;
            this.name = name;
            this.department = department;
            this.projectId = projectId;
            this.date = date;
            this.taskCategory = taskCategory;
            this.hoursWorked = hoursWorked;
            this.remarks = remarks;
        }
    }

    public static void main(String[] args) {
        try {
            String inputPath = "C:\\Users\\yuva tpt\\Desktop\\Sample_Employee_WorkLogs.xlsx"; // Update this path as needed
            List<EmployeeWorkLog> logs = readExcelData(inputPath);

            if (logs.isEmpty()) {
                System.out.println("No data found in the input file.");
                return;
            }

            // Task 2: Top 3 most time-consuming tasks per department
            Map<String, List<EmployeeWorkLog>> topTasksPerDept = logs.stream()
                    .filter(log -> log.department != null && log.taskCategory != null)
                    .collect(Collectors.groupingBy(log -> log.department,
                            Collectors.collectingAndThen(Collectors.toList(),
                                    list -> list.stream()
                                            .sorted(Comparator.comparingDouble((EmployeeWorkLog e) -> e.hoursWorked).reversed())
                                            .limit(3)
                                            .collect(Collectors.toList()))));

            // Task 10: "Bug Fix" tasks grouped by category and day of week
            Map<String, Map<String, Double>> bugFixGrouped = logs.stream()
                    .filter(log -> log.taskCategory != null && log.taskCategory.toLowerCase().contains("bug"))
                    .collect(Collectors.groupingBy(log -> log.taskCategory,
                            Collectors.groupingBy(log -> log.date.getDayOfWeek().toString(),
                                    Collectors.summingDouble(log -> log.hoursWorked))));

            // Task 13: Daily average hours trend in last 30 working days per employee
            LocalDate maxDate = logs.stream().map(log -> log.date).max(LocalDate::compareTo).orElse(LocalDate.now());
            LocalDate fromDate = maxDate.minusDays(30);
            Map<String, Double> avgHoursPerEmployee = logs.stream()
                    .filter(log -> !log.date.isBefore(fromDate))
                    .collect(Collectors.groupingBy(log -> log.employeeId,
                            Collectors.averagingDouble(log -> log.hoursWorked)));

            // Task 17: Department switches mid-month
            Map<String, Long> deptSwitches = logs.stream()
                    .filter(log -> log.date.getDayOfMonth() <= 15 && log.department != null)
                    .collect(Collectors.groupingBy(
                            log -> log.employeeId + "_" + log.date.getYear() + "-" + String.format("%02d", log.date.getMonthValue()),
                            Collectors.mapping(log -> log.department, Collectors.toSet())
                    ))
                    .entrySet().stream()
                    .filter(e -> e.getValue().size() > 1)
                    .collect(Collectors.groupingBy(
                            e -> e.getKey().split("_")[1],
                            Collectors.counting()
                    ));

            // Task 33: Project switches more than once per month
            Map<String, Long> projectSwitches = logs.stream()
                    .filter(log -> log.projectId != null)
                    .collect(Collectors.groupingBy(
                            log -> log.employeeId + "_" + log.date.getYear() + "-" + String.format("%02d", log.date.getMonthValue()),
                            Collectors.mapping(log -> log.projectId, Collectors.toSet())
                    ))
                    .entrySet().stream()
                    .filter(e -> e.getValue().size() > 1)
                    .collect(Collectors.groupingBy(
                            e -> e.getKey().split("_")[1],
                            Collectors.counting()
                    ));
            
            // new Task
            Map<String, String> employeeIdToName = logs.stream()
                    .collect(Collectors.toMap(r -> r.employeeId, r -> r.name, (a, b) -> a));

                // Group by EmployeeId and YearMonth, and count distinct projects
                Map<String, Map<String, Long>> empMonthProjectCount = logs.stream()
                    .collect(Collectors.groupingBy(
                        r -> r.employeeId,
                        Collectors.groupingBy(
                            r -> r.date.getYear() + "-" + String.format("%02d", r.date.getMonthValue()),
                            Collectors.mapping(r -> r.projectId, Collectors.toSet())
                        )
                    )).entrySet().stream()
                    .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().entrySet().stream()
                                .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    v -> (long) v.getValue().size()
                                ))
                    ));

                // Filter employees who changed projects more than once in any month
                List<String> result = empMonthProjectCount.entrySet().stream()
                    .filter(e -> e.getValue().values().stream().anyMatch(count -> count > 1))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            // Export results to Excel
            exportToExcel(topTasksPerDept, bugFixGrouped, avgHoursPerEmployee, deptSwitches, projectSwitches ,employeeIdToName ,result  );
            
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<EmployeeWorkLog> readExcelData(String filePath) throws Exception {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        try (InputStream is = Files.newInputStream(Paths.get(filePath));
             Workbook workbook = new XSSFWorkbook(is)) {
            
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            
            // Skip header if exists
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                try {
                    String empId = getStringCellValue(row.getCell(0));
                    String name = getStringCellValue(row.getCell(1));
                    String dept = getStringCellValue(row.getCell(2));
                    String projId = getStringCellValue(row.getCell(3));

                    LocalDate date = parseDateCell(row.getCell(4), formatter);
                    String task = getStringCellValue(row.getCell(5));
                    double hrs = getNumericCellValue(row.getCell(6));
                    String remarks = getStringCellValue(row.getCell(7));

                    logs.add(new EmployeeWorkLog(empId, name, dept, projId, date, task, hrs, remarks));
                } catch (Exception e) {
                    System.out.println("⚠️ Skipping row " + row.getRowNum() + ": " + e.getMessage());
                }
            }
        }
        return logs;
    }

    private static String getStringCellValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue()).trim();
        }
        return "";
    }

    private static double getNumericCellValue(Cell cell) {
        if (cell == null) return 0.0;
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
        return 0.0;
    }

    private static LocalDate parseDateCell(Cell cell, DateTimeFormatter formatter) {
        if (cell == null) return LocalDate.now();
        
        try {
            if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                return cell.getLocalDateTimeCellValue().toLocalDate();
            } else {
                String dateStr = cell.getStringCellValue().trim();
                return LocalDate.parse(dateStr, formatter);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Using current date for invalid date cell: " + e.getMessage());
            return LocalDate.now();
        }
    }

    public static void exportToExcel(
            Map<String, List<EmployeeWorkLog>> topTasks,
            Map<String, Map<String, Double>> bugFix,
            Map<String, Double> avgHours,
            Map<String, Long> deptSwitch,
            Map<String, Long> projSwitch,
            Map<String, String> employeeIdToName,
            List<String> result
    ) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream("Employee_Analytics_Report_new.xlsx")) {
            
            // Sheet 1: Top Tasks Per Dept
            Sheet topSheet = workbook.createSheet("Top Tasks Per Dept");
            int rowNum = 0;
            Row headerRow = topSheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Department");
            headerRow.createCell(1).setCellValue("Task Category");
            headerRow.createCell(2).setCellValue("Hours Worked");
            
            for (Map.Entry<String, List<EmployeeWorkLog>> entry : topTasks.entrySet()) {
                for (EmployeeWorkLog log : entry.getValue()) {
                    Row row = topSheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(log.department);
                    row.createCell(1).setCellValue(log.taskCategory);
                    row.createCell(2).setCellValue(log.hoursWorked);
                }
            }

            // Sheet 2: Bug Fix Grouped
            Sheet bugSheet = workbook.createSheet("Bug Fix Grouped");
            rowNum = 0;
            headerRow = bugSheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Category");
            headerRow.createCell(1).setCellValue("Day of Week");
            headerRow.createCell(2).setCellValue("Total Hours");
            
            for (String cat : bugFix.keySet()) {
                for (String day : bugFix.get(cat).keySet()) {
                    Row row = bugSheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(cat);
                    row.createCell(1).setCellValue(day);
                    row.createCell(2).setCellValue(bugFix.get(cat).get(day));
                }
            }

            // Sheet 3: Avg Daily Hours
            Sheet avgSheet = workbook.createSheet("Avg Daily Hours");
            rowNum = 0;
            headerRow = avgSheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Employee ID");
            headerRow.createCell(1).setCellValue("Average Hours");
            
            for (Map.Entry<String, Double> e : avgHours.entrySet()) {
                Row row = avgSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(e.getKey());
                row.createCell(1).setCellValue(e.getValue());
            }

            // Sheet 4: Dept Switches
            Sheet deptSheet = workbook.createSheet("Dept Switches");
            rowNum = 0;
            headerRow = deptSheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Month");
            headerRow.createCell(1).setCellValue("Switch Count");
            
            for (Map.Entry<String, Long> e : deptSwitch.entrySet()) {
                Row row = deptSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(e.getKey());
                row.createCell(1).setCellValue(e.getValue());
            }

            // Sheet 5: Project Switches
            Sheet projSheet = workbook.createSheet("Project Switches");
            rowNum = 0;
            headerRow = projSheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Month");
            headerRow.createCell(1).setCellValue("Switch Count");
            
            for (Map.Entry<String, Long> e : projSwitch.entrySet()) {
                Row row = projSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(e.getKey());
                row.createCell(1).setCellValue(e.getValue());
            }
            
            // sheet employee switch
         // Sheet 6: Frequent Project Changers
            Sheet changerSheet = workbook.createSheet("Frequent Project Changers");
            rowNum = 0;
            headerRow = changerSheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Employee ID");
            headerRow.createCell(1).setCellValue("Employee Name");

            // Write data
            for (String empId : result) {
                Row row = changerSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(empId);
                row.createCell(1).setCellValue(employeeIdToName.get(empId));
            }
            
            workbook.write(fos);
            System.out.println("✅ Report generated: Employee_Analytics_Report_new.xlsx");
        }
    }
}