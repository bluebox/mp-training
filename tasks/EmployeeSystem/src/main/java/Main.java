import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static class EmployeeWorkLog {
        private String employeeId;
        private String name;
        private String department;
        private String projectId;
        private LocalDate date;
        private String taskCategory;
        private double hoursWorked;
        private String remarks;

        EmployeeWorkLog(String employeeId, String name, String department, String projectId,LocalDate date, String taskCategory, double hoursWorked, String remarks) {
            this.employeeId = employeeId;
            this.name = name;
            this.department = department;
            this.projectId = projectId;
            this.date = date;
            this.taskCategory = taskCategory;
            this.hoursWorked = hoursWorked;
            this.remarks = remarks;
        }

        public String getEmployeeId() { return employeeId; }
        public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDepartment() { return department; }
        public void setDepartment(String department) { this.department = department; }
        public String getProjectId() { return projectId; }
        public void setProjectId(String projectId) { this.projectId = projectId; }
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
        public String getTaskCategory() { return taskCategory; }
        public void setTaskCategory(String taskCategory) { this.taskCategory = taskCategory; }
        public double getHoursWorked() { return hoursWorked; }
        public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }
        public String getRemarks() { return remarks; }
        public void setRemarks(String remarks) { this.remarks = remarks; }
    }

    public static void main(String[] args) {
        String inputFile = "Sample_Employee_WorkLogs.xlsx";
        List<EmployeeWorkLog> logs = readExcelFile(inputFile);
        System.out.println(1);
        findZeroHourConsecutiveDays(logs, "zero_hour_consecutive_days.xlsx");
        System.out.println(1);
        find3PlusCategoryEmployees(logs, "3plus_category_employees.xlsx");        System.out.println(1);

        calculateCategoryPercentages(logs, "category_percentages.xlsx");        System.out.println(1);

        findCriticalProjects(logs, "critical_projects.xlsx");        System.out.println(1);

        System.out.println("Processing complete.");
    }

    private static List<EmployeeWorkLog> readExcelFile(String filePath) {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                String employeeId = getCellValue(row.getCell(0));
                String name = getCellValue(row.getCell(1));
                String department = getCellValue(row.getCell(2));
                String projectId = getCellValue(row.getCell(3));
                String dateStr = getCellValue(row.getCell(4));
                String taskCategory = getCellValue(row.getCell(5));
                String hoursStr = getCellValue(row.getCell(6));
                String remarks = getCellValue(row.getCell(7));

                LocalDate date = LocalDate.parse(dateStr, dateFormatter);
//                if (dateStr != null && !dateStr.isEmpty()) {
//                    try {
//                        date = LocalDate.parse(dateStr, dateFormatter);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }

                double hoursWorked = Double.parseDouble(hoursStr);
//                if (hoursStr != null && !hoursStr.isEmpty()) {
//                    try {
//                        hoursWorked = Double.parseDouble(hoursStr);
//                    } catch (NumberFormatException e) {
//                        System.out.println("Warning: Invalid hours '" + hoursStr + "' in row " + (row.getRowNum() + 1));
//                    }
//                }

                EmployeeWorkLog log = new EmployeeWorkLog(employeeId, name, department, projectId, date, taskCategory, hoursWorked, remarks);
                logs.add(log);
            }

            workbook.close();
            file.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return logs;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toLocalDate()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }
                return String.valueOf(cell.getNumericCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    private static void findZeroHourConsecutiveDays(List<EmployeeWorkLog> logs, String filePath) {
        Map<String, List<EmployeeWorkLog>> logsByEmployee = logs.stream()
                .filter(log -> log.getEmployeeId() != null)
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId));

        List<String[]> results = new ArrayList<>();
        results.add(new String[]{"Employee ID", "Name", "Consecutive Zero-Hour Days"});

        for (Map.Entry<String, List<EmployeeWorkLog>> entry : logsByEmployee.entrySet()) {
            System.out.println("Processing employee: " + entry.getKey());
            String employeeId = entry.getKey();
            String name = entry.getValue().get(0).getName();
            List<LocalDate> datesWithWork = entry.getValue().stream()
                    .filter(log -> log.getDate() != null)
                    .map(EmployeeWorkLog::getDate)
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());

//            if (datesWithWork.isEmpty()) {
//                System.out.println("No valid dates for employee: " + employeeId);
//                continue;
//            }

            Map<LocalDate, Double> hoursByDate = entry.getValue().stream()
                    .filter(log -> log.getDate() != null)
                    .collect(Collectors.toMap(
                            EmployeeWorkLog::getDate,
                            EmployeeWorkLog::getHoursWorked,
                            Double::sum
                    ));

            int tempCount = 0;
            int maxCount = 0;
            LocalDate prevDate = null;

            for (LocalDate date : datesWithWork) {
                if (prevDate != null) {
                    long daysBetween = ChronoUnit.DAYS.between(prevDate, date);
                    if (daysBetween > 1) {
                        tempCount += (int) (daysBetween - 1);
                        maxCount = Math.max(maxCount, tempCount);
                    }
                }
                if (hoursByDate.get(date) == 0.0) {
                    tempCount++;
                } else {
                    maxCount = Math.max(maxCount, tempCount);
                    tempCount = 0;
                }
                prevDate = date;
            }

            maxCount = Math.max(maxCount, tempCount);

            if (maxCount >= 3) {
                System.out.println("Found " + maxCount + " consecutive zero-hour days for employee: " + employeeId);
                results.add(new String[]{employeeId, name, String.valueOf(maxCount)});
            }
        }

        writeExcelFile(results, filePath, "ZeroHourDays");
    }
    private static void find3PlusCategoryEmployees(List<EmployeeWorkLog> logs, String filePath) {
        Map<String, List<EmployeeWorkLog>> logsByEmployee = logs.stream()
                .filter(log -> log.getEmployeeId() != null && log.getDate() != null)
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId));

        List<String[]> results = new ArrayList<>();
        results.add(new String[]{"Employee ID", "Name", "Week Start", "Task Categories"});

        for (Map.Entry<String, List<EmployeeWorkLog>> entry : logsByEmployee.entrySet()) {
            String employeeId = entry.getKey();
            String name = entry.getValue().get(0).getName();
            List<EmployeeWorkLog> employeeLogs = entry.getValue();

            Set<LocalDate> weekStarts = employeeLogs.stream()
                    .map(log -> log.getDate().minusDays(log.getDate().getDayOfWeek().getValue() - 1))
                    .collect(Collectors.toSet());

            for (LocalDate weekStart : weekStarts) {
                LocalDate weekEnd = weekStart.plusDays(6);
                Set<String> categories = employeeLogs.stream()
                        .filter(log -> !log.getDate().isBefore(weekStart) && !log.getDate().isAfter(weekEnd))
                        .map(EmployeeWorkLog::getTaskCategory)
                        .filter(category -> category != null && !category.isEmpty())
                        .collect(Collectors.toSet());

                if (categories.size() >= 3) {
                    results.add(new String[]{employeeId, name, weekStart.toString(), String.join(", ", categories)});
                }
            }
        }

        writeExcelFile(results, filePath, "3PlusCategoryEmployees");
    }

    private static void calculateCategoryPercentages(List<EmployeeWorkLog> logs, String filePath) {
        Map<String, Map<String, Double>> hoursByEmployeeAndCategory = logs.stream()
                .filter(log -> log.getEmployeeId() != null && log.getTaskCategory() != null)
                .collect(Collectors.groupingBy(
                        EmployeeWorkLog::getEmployeeId,
                        Collectors.groupingBy(
                                EmployeeWorkLog::getTaskCategory,
                                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
                        )
                ));

        Map<String, String> employeeNames = logs.stream()
                .filter(log -> log.getEmployeeId() != null)
                .collect(Collectors.toMap(
                        EmployeeWorkLog::getEmployeeId,
                        EmployeeWorkLog::getName,
                        (n1, n2) -> n1
                ));

        List<String[]> results = new ArrayList<>();
        results.add(new String[]{"Employee ID", "Name", "Task Category", "Hours", "Percentage"});

        for (Map.Entry<String, Map<String, Double>> employeeEntry : hoursByEmployeeAndCategory.entrySet()) {
            String employeeId = employeeEntry.getKey();
            String name = employeeNames.getOrDefault(employeeId, "");
            Map<String, Double> categoryHours = employeeEntry.getValue();
            double totalHours = categoryHours.values().stream().mapToDouble(Double::doubleValue).sum();

            for (Map.Entry<String, Double> categoryEntry : categoryHours.entrySet()) {
                String category = categoryEntry.getKey();
                double hours = categoryEntry.getValue();
                double percentage = totalHours > 0 ? (hours / totalHours * 100) : 0.0;
                results.add(new String[]{employeeId, name, category, String.format("%.2f", hours), String.format("%.2f", percentage)});
            }
        }

        writeExcelFile(results, filePath, "CategoryPercentages");
    }

    private static void findCriticalProjects(List<EmployeeWorkLog> logs, String filePath) {
        Map<String, List<EmployeeWorkLog>> logsByProject = logs.stream()
                .filter(log -> log.getProjectId() != null)
                .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId));

        List<String[]> results = new ArrayList<>();
        results.add(new String[]{"Project ID", "Employee Count", "Total Hours"});

        for (Map.Entry<String, List<EmployeeWorkLog>> entry : logsByProject.entrySet()) {
            String projectId = entry.getKey();
            Set<String> employees = entry.getValue().stream()
                    .map(EmployeeWorkLog::getEmployeeId)
                    .filter(id -> id != null)
                    .collect(Collectors.toSet());
            double totalHours = entry.getValue().stream()
                    .mapToDouble(EmployeeWorkLog::getHoursWorked)
                    .sum();

            if (employees.size() > 5 && totalHours > 100) {
                results.add(new String[]{projectId, String.valueOf(employees.size()), String.format("%.2f", totalHours)});
            }
        }

        writeExcelFile(results, filePath, "CriticalProjects");
    }

    private static void writeExcelFile(List<String[]> data, String filePath, String sheetName) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(sheetName);
            int rowNumber = 0;

            for (String[] rowData : data) {
                Row row = sheet.createRow(rowNumber++);
                for (int i = 0; i < rowData.length; i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(rowData[i]);
                }
            }

            for (int i = 0; i < data.get(0).length; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}