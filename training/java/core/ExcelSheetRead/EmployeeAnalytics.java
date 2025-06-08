package ExcelSheetRead;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeAnalytics {

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

    public static void main(String[] args) throws Exception {
        String inputPath = "Sample_Employee_WorkLogs.xlsx";
        List<EmployeeWorkLog> logs = readExcelData(inputPath);

        // Task 2: Top 3 most time-consuming tasks per department
        Map<String, List<EmployeeWorkLog>> topTasksPerDept = logs.stream()
                .collect(Collectors.groupingBy(log -> log.department,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble((EmployeeWorkLog e) -> e.hoursWorked).reversed())
                                        .limit(3)
                                        .collect(Collectors.toList()))));

        // Task 10: "Bug Fix" tasks grouped by category and day of week
        Map<String, Map<String, Double>> bugFixGrouped = logs.stream()
                .filter(log -> log.taskCategory.toLowerCase().contains("bug"))
                .collect(Collectors.groupingBy(log -> log.taskCategory,
                        Collectors.groupingBy(log -> log.date.getDayOfWeek().toString(),
                                Collectors.summingDouble(log -> log.hoursWorked))));

        // Task 13: Daily average hours trend in last 30 working days per employee
        LocalDate maxDate = logs.stream().map(log -> log.date).max(LocalDate::compareTo).orElse(LocalDate.now());
        LocalDate fromDate = maxDate.minusDays(45);
        Map<String, Double> avgHoursPerEmployee = logs.stream()
                .filter(log -> log.date.isAfter(fromDate))
                .collect(Collectors.groupingBy(log -> log.employeeId,
                        Collectors.averagingDouble(log -> log.hoursWorked)));

        // Task 17: Department switches mid-month
        Map<String, Long> deptSwitches = logs.stream()
                .filter(log -> log.date.getDayOfMonth() <= 15)
                .collect(Collectors.groupingBy(log -> log.employeeId + log.date.getMonth(),
                        Collectors.mapping(log -> log.department, Collectors.toSet())))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.groupingBy(e -> e.getKey().substring(0, 7), Collectors.counting()));

        // Task 33: Employees changing projects more than once/month
        Map<String, Long> projectSwitches = logs.stream()
                .collect(Collectors.groupingBy(log -> log.employeeId + log.date.getMonth(),
                        Collectors.mapping(log -> log.projectId, Collectors.toSet())))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.groupingBy(e -> e.getKey().substring(0, 7), Collectors.counting()));

        // Export results to Excel
        exportToExcel(topTasksPerDept, bugFixGrouped, avgHoursPerEmployee, deptSwitches, projectSwitches);
    }

    public static List<EmployeeWorkLog> readExcelData(String filePath) throws Exception {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String empId = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();
            String dept = row.getCell(2).getStringCellValue();
            String projId = row.getCell(3).getStringCellValue();
            LocalDate date = row.getCell(4).getLocalDateTimeCellValue().toLocalDate();
            String task = row.getCell(5).getStringCellValue();
            double hrs = row.getCell(6).getNumericCellValue();
            String remarks = row.getCell(7).getStringCellValue();

            logs.add(new EmployeeWorkLog(empId, name, dept, projId, date, task, hrs, remarks));
        }
        workbook.close();
        return logs;
    }

    public static void exportToExcel(
            Map<String, List<EmployeeWorkLog>> topTasks,
            Map<String, Map<String, Double>> bugFix,
            Map<String, Double> avgHours,
            Map<String, Long> deptSwitch,
            Map<String, Long> projSwitch
    ) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet topSheet = workbook.createSheet("Top Tasks Per Dept");
        int rowNum = 0;
        for (Map.Entry<String, List<EmployeeWorkLog>> entry : topTasks.entrySet()) {
            for (EmployeeWorkLog log : entry.getValue()) {
                Row row = topSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(log.department);
                row.createCell(1).setCellValue(log.taskCategory);
                row.createCell(2).setCellValue(log.hoursWorked);
            }
        }

        Sheet bugSheet = workbook.createSheet("Bug Fix Grouped");
        rowNum = 0;
        for (String cat : bugFix.keySet()) {
            for (String day : bugFix.get(cat).keySet()) {
                Row row = bugSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(cat);
                row.createCell(1).setCellValue(day);
                row.createCell(2).setCellValue(bugFix.get(cat).get(day));
            }
        }

        Sheet avgSheet = workbook.createSheet("Avg Daily Hours");
        rowNum = 0;
        for (Map.Entry<String, Double> e : avgHours.entrySet()) {
            Row row = avgSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(e.getKey());
            row.createCell(1).setCellValue(e.getValue());
        }

        Sheet deptSheet = workbook.createSheet("Dept Switches");
        rowNum = 0;
        for (Map.Entry<String, Long> e : deptSwitch.entrySet()) {
            Row row = deptSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(e.getKey());
            row.createCell(1).setCellValue(e.getValue());
        }

        Sheet projSheet = workbook.createSheet("Project Switches");
        rowNum = 0;
        for (Map.Entry<String, Long> e : projSwitch.entrySet()) {
            Row row = projSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(e.getKey());
            row.createCell(1).setCellValue(e.getValue());
        }

        try (FileOutputStream fos = new FileOutputStream("Employee_Analytics_Report.xlsx")) {
            workbook.write(fos);
        }
        workbook.close();
    }
}

