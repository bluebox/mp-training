package Project;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmployeeWorkLogAnalyzer {

    public static void main(String[] args) throws Exception {
        List<EmployeeWorkLog> logs = readExcel("files/EmployeeDetails3.xlsx");

        engineeringMultipleProjects(logs);
        logsFromMarchToMay(logs);
        groupByDepartmentAndProject(logs);
        dailyAverageTrend(logs);
        consistencyAnalysis(logs);
    }

    static List<EmployeeWorkLog> readExcel(String path) throws IOException {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        try (InputStream fis = Files.newInputStream(Paths.get(path));
            Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                String empId = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String dept = row.getCell(2).getStringCellValue();
                String projId = row.getCell(3).getStringCellValue();

                LocalDate date = null;
                Cell dateCell = row.getCell(4);
                if (dateCell != null) {
                    if (dateCell.getCellType() == CellType.NUMERIC) {
                        date = dateCell.getLocalDateTimeCellValue().toLocalDate();
                    } else if (dateCell.getCellType() == CellType.STRING) {
                        try {
                            date = LocalDate.parse(dateCell.getStringCellValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        } catch (Exception e) {
                            System.out.println("Invalid date at row " + i + ": " + dateCell.getStringCellValue());
                        }
                    }
                }

                String category = row.getCell(5).getStringCellValue();

                double hours = 0;
                Cell hoursCell = row.getCell(6);
                if (hoursCell != null) {
                    if (hoursCell.getCellType() == CellType.NUMERIC) {
                        hours = hoursCell.getNumericCellValue();
                    } else if (hoursCell.getCellType() == CellType.STRING) {
                        try {
                            hours = Double.parseDouble(hoursCell.getStringCellValue());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid hours at row " + i + ": " + hoursCell.getStringCellValue());
                        }
                    }
                }

                String remarks = row.getCell(7).getStringCellValue();

                logs.add(new EmployeeWorkLog(empId, name, dept, projId, date, category, hours, remarks));

            }
        }
        return logs;
    }

    static void engineeringMultipleProjects(List<EmployeeWorkLog> logs) throws IOException {
        Map<String, Long> result = logs.stream()
            .filter(log -> log.getDepartment().equalsIgnoreCase("Engineering"))
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                Collectors.mapping(EmployeeWorkLog::getProjectId, Collectors.toSet())))
            .entrySet().stream()
            .filter(e -> e.getValue().size() > 2)
            .collect(Collectors.toMap(Map.Entry::getKey, e -> (long) e.getValue().size()));

        writeCSV("engineering_multiple_projects.csv", List.of("Employee ID", "Project Count"),
            result.entrySet().stream()
                .map(e -> List.of(e.getKey(), String.valueOf(e.getValue())))
                .toList());
    }

    static void logsFromMarchToMay(List<EmployeeWorkLog> logs) throws IOException {
        Map<String, Double> result = logs.stream()
            .filter(log -> {
                int month = log.getDate().getMonthValue();
                return month >= 3 && month <= 5;
            })
            .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId,
                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)))
            .entrySet().stream()
            .filter(e -> e.getValue() > 10)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        writeCSV("march_may_project_hours.csv", List.of("Project ID", "Total Hours"),
            result.entrySet().stream()
                .map(e -> List.of(e.getKey(), String.valueOf(e.getValue())))
                .toList());
    }

    static void groupByDepartmentAndProject(List<EmployeeWorkLog> logs) throws IOException {
        Map<String, Map<String, Double>> grouped = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getDepartment,
                Collectors.groupingBy(EmployeeWorkLog::getProjectId,
                    Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))));

        List<List<String>> rows = new ArrayList<>();
        for (String dept : grouped.keySet()) {
            grouped.get(dept).entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(e -> rows.add(List.of(dept, e.getKey(), String.format("%.2f", e.getValue()))));
        }
        writeCSV("department_project_hours.csv", List.of("Department", "Project ID", "Total Hours"), rows);
    }

    static void dailyAverageTrend(List<EmployeeWorkLog> logs) throws IOException {
        LocalDate latest = logs.stream().map(EmployeeWorkLog::getDate).max(LocalDate::compareTo).orElse(LocalDate.now());
        LocalDate start = latest.minusDays(30);

        Map<String, Double> result = logs.stream()
            .filter(log -> log.getDate().isAfter(start))
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                Collectors.averagingDouble(EmployeeWorkLog::getHoursWorked)));

        writeCSV("daily_avg_hours_30days.csv", List.of("Employee ID", "Avg Hours"),
            result.entrySet().stream()
                .map(e -> List.of(e.getKey(), String.format("%.2f", e.getValue())))
                .toList());
    }

    static void consistencyAnalysis(List<EmployeeWorkLog> logs) throws IOException {
        Map<String, Double> result = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getTaskCategory,
                Collectors.collectingAndThen(Collectors.mapping(EmployeeWorkLog::getHoursWorked,
                    Collectors.toList()), EmployeeWorkLogAnalyzer::calculateVariance)));

        var sorted = result.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toList());

        writeCSV("task_category_consistency.csv", List.of("Task Category", "Variance"),
            sorted.stream()
                .map(e -> List.of(e.getKey(), String.format("%.2f", e.getValue())))
                .toList());
    }

    static double calculateVariance(List<Double> values) {
        double avg = values.stream().mapToDouble(d -> d).average().orElse(0);
        return values.stream().mapToDouble(d -> (d - avg) * (d - avg)).average().orElse(0);
    }

    static void writeCSV(String fileName, List<String> headers, List<List<String>> rows) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(String.join(",", headers));
            for (List<String> row : rows) {
                writer.println(String.join(",", row));
            }
        }
    }
}

