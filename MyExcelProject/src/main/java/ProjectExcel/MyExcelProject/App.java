package ProjectExcel.MyExcelProject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    public static class EmployeeWorkLog {
        private String employeeId;
        private String name;
        private String department;
        private String projectId;
        private String taskCategory;
        private String remarks;
        private LocalDate date;
        private double hoursWorked;

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

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getTaskCategory() {
            return taskCategory;
        }

        public void setTaskCategory(String taskCategory) {
            this.taskCategory = taskCategory;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public double getHoursWorked() {
            return hoursWorked;
        }

        public void setHoursWorked(double hoursWorked) {
            this.hoursWorked = hoursWorked;
        }

    }

    public static void main(String[] args) {
        try {
            String inputPath = "C:\\Users\\saket\\Desktop\\Medplus caseStudies\\Sample_Employee_WorkLogs.xlsx"
            		+ "";
            List<EmployeeWorkLog> logs = readExcelData(inputPath);

            if (logs.isEmpty()) {
                System.out.println("No data found in the input file.");
                return;
            }

            

            System.out.println("--------------------------");


            // Task 2: Top 3 most time-consuming tasks per department
            Map<String, List<EmployeeWorkLog>> topTasksPerDept = logs.stream()
                    .filter(log -> log.getDepartment() != null && log.getTaskCategory() != null)
                    .collect(Collectors.groupingBy(log -> log.getDepartment(),
                            Collectors.collectingAndThen(Collectors.toList(),
                                    list -> list.stream()
                                            .sorted(Comparator.comparingDouble(EmployeeWorkLog::getHoursWorked).reversed())
                                            .limit(3)
                                            .collect(Collectors.toList()))));
            
            
            
            
         // Task 5: Logs with "urgent" or "critical" in remarks; sort by employee name
            List<EmployeeWorkLog> urgentCriticalLogs = logs.stream()
                    .filter(log -> log.getRemarks() != null &&
                            (log.getRemarks().toLowerCase().contains("urgent") ||
                             log.getRemarks().toLowerCase().contains("critical")))
                    .sorted(Comparator.comparing(EmployeeWorkLog::getName))
                    .collect(Collectors.toList());

            // Task 7: Identify employees logging >10 hrs in a day; group by employee
            Map<String, List<EmployeeWorkLog>> overworkedLogs = logs.stream()
                    .filter(log -> log.getHoursWorked() > 10.0)
                    .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId));

            // Task 26: Weekend logs; summarize weekend hours
            Map<String, Double> weekendSummary = logs.stream()
                    .filter(log -> {
                        DayOfWeek day = log.getDate().getDayOfWeek();
                        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
                    })
                    .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                            Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)));

            // Task 30: Analyze "meeting" time from remarks
            Map<String, Double> meetingAnalysis = logs.stream()
            	    .filter(log -> log.getRemarks() != null && log.getRemarks().toLowerCase().contains("meeting"))
            	    .collect(Collectors.groupingBy(
            	        EmployeeWorkLog::getRemarks,
            	        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
            	    ));

            // Export results to Excel
            exportToExcel(topTasksPerDept,urgentCriticalLogs,overworkedLogs,weekendSummary ,meetingAnalysis );

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
                    System.out.println("⚠ Skipping row " + row.getRowNum() + ": " + e.getMessage());
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
            System.out.println("Using current date for invalid date cell: " + e.getMessage());
            return LocalDate.now();
        }
    }

    public static void exportToExcel(
            Map<String, List<EmployeeWorkLog>> topTasks,
            List<EmployeeWorkLog> urgentCritical,
            Map<String, List<EmployeeWorkLog>> overworked,
            Map<String, Double> weekendSummary,
            Map<String, Double> meetingAnalysis
           
    ) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream("Employee_Analytics_Report_new_a_saketh.xlsx")) {

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
                    row.createCell(0).setCellValue(log.getDepartment());
                    row.createCell(1).setCellValue(log.getTaskCategory());
                    row.createCell(2).setCellValue(log.getHoursWorked());
                }
            }

            
         // Sheet 2: Urgent/Critical Logs
            Sheet urgentSheet = workbook.createSheet("Urgent or Critical Logs");
            rowNum = 0;
            headerRow = urgentSheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Employee Name");
            headerRow.createCell(1).setCellValue("Employee ID");
            headerRow.createCell(2).setCellValue("Remarks");
            headerRow.createCell(3).setCellValue("Date");

            for (EmployeeWorkLog log : urgentCritical) {
                Row row = urgentSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(log.getName());
                row.createCell(1).setCellValue(log.getEmployeeId());
                row.createCell(2).setCellValue(log.getRemarks());
                row.createCell(3).setCellValue(log.getDate().toString());
            }

            // Sheet 3: >10 hrs logs
            Sheet overworkSheet = workbook.createSheet(">10 Hrs Logs");
            rowNum = 0;
            headerRow = overworkSheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Employee ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Date");
            headerRow.createCell(3).setCellValue("Hours Worked");

            for (Map.Entry<String, List<EmployeeWorkLog>> entry : overworked.entrySet()) {
                for (EmployeeWorkLog log : entry.getValue()) {
                    Row row = overworkSheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(log.getEmployeeId());
                    row.createCell(1).setCellValue(log.getName());
                    row.createCell(2).setCellValue(log.getDate().toString());
                    row.createCell(3).setCellValue(log.getHoursWorked());
                }
            }

            // Sheet 4: Weekend Hours Summary
            Sheet weekendSheet = workbook.createSheet("Weekend Summary");
            rowNum = 0;
            headerRow = weekendSheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Employee ID");
            headerRow.createCell(1).setCellValue("Weekend Hours");

            for (Map.Entry<String, Double> e : weekendSummary.entrySet()) {
                Row row = weekendSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(e.getKey());
                row.createCell(1).setCellValue(e.getValue());
            }

            // Sheet 5: Meeting Hours Analysis
            Sheet meetingSheet = workbook.createSheet("Meeting Analysis");
            rowNum = 0;
            headerRow = meetingSheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Remarks");
            headerRow.createCell(1).setCellValue("Total Meeting Hours");

            for (Map.Entry<String, Double> e : meetingAnalysis.entrySet()) {
                Row row = meetingSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(e.getKey());
                row.createCell(1).setCellValue(e.getValue());
            }
            

            workbook.write(fos);
            System.out.println("Report generated: Employee_Analytics_Report_new_a_saketh.xlsx");
        }
    }
}