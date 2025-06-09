

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class EmployeeManipulation {

    public static void main(String[] args) {
        try {
            String inputFile = "/home/developer/eclipse-workspace/EmployeeProductivityAnalysisSystem/Sample_Employee_WorkLogs.xlsx";

            FileInputStream fis = new FileInputStream(inputFile);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            List<Employee> employees = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // skip header

                LocalDate date = null;
                Cell dateCell = row.getCell(4);
                if (dateCell != null) {
                    if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
                        date = dateCell.getLocalDateTimeCellValue().toLocalDate();
                    } else if (dateCell.getCellType() == CellType.STRING) {
                        date = LocalDate.parse(dateCell.getStringCellValue());
                    }
                }

                Employee emp = new Employee(
                        getCellString(row.getCell(0)),
                        getCellString(row.getCell(1)),
                        getCellString(row.getCell(2)),
                        getCellString(row.getCell(3)),
                        date,
                        getCellString(row.getCell(5)),
                        getCellDouble(row.getCell(6)),
                        getCellString(row.getCell(7))
                );

                employees.add(emp);
            }
            workbook.close();

            System.out.println("Total employees loaded: " + employees.size());

            // Change these output paths as needed
            String baseOutputPath = "/home/developer/eclipse-workspace/EmployeeProductivityAnalysisSystem/";

            operation3(employees, baseOutputPath + "Operation3.xlsx");
            operation10(employees, baseOutputPath + "Operation10.xlsx");
            operation18(employees, baseOutputPath + "Operation18.xlsx");
            operation19(employees, baseOutputPath + "Operation19.xlsx");

            System.out.println("All operations completed and CSV files generated.");

        } catch (IOException | EncryptedDocumentException  e) {
            e.printStackTrace();
        }
    }

    // Utility methods for safe cell reads
    private static String getCellString(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        }
        return cell.toString().trim();
    }

    private static double getCellDouble(Cell cell) {
        if (cell == null) return 0.0;
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }
        try {
            return Double.parseDouble(cell.getStringCellValue());
        } catch (Exception e) {
            return 0.0;
        }
    }

    // Operation 3: Logs from Mar-May; group by project; total hours > 100
    public static void operation3(List<Employee> employees, String outputFile) throws IOException {
        Map<String, Double> projectHours = employees.stream()
                .filter(e -> e.date != null && (e.date.getMonthValue() >= 3 && e.date.getMonthValue() <= 5))
                .collect(Collectors.groupingBy(e -> e.projectId,
                        Collectors.summingDouble(e -> e.hoursWorked)));

        Map<String, Double> filtered = projectHours.entrySet().stream()
                .filter(entry -> entry.getValue() > 20)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        try (PrintWriter pw = new PrintWriter(outputFile)) {
            pw.println("Project ID,Total Hours Worked");
            for (Map.Entry<String, Double> entry : filtered.entrySet()) {
                pw.printf("%s,%.2f\n", entry.getKey(), entry.getValue());
            }
        }
        System.out.println("Operation 3 completed: " + outputFile);
    }

    // Operation 10: "Bug Fix" tasks grouped by category and day of week
    public static void operation10(List<Employee> employees, String outputFile) throws IOException {
        Map<String, Map<String, Long>> grouped = employees.stream()
                .filter(e -> "Bug Fix".equalsIgnoreCase(e.taskCategory) && e.date != null)
                .collect(Collectors.groupingBy(e -> e.taskCategory,
                        Collectors.groupingBy(e -> e.date.getDayOfWeek().toString(), Collectors.counting())));

        try (PrintWriter pw = new PrintWriter(outputFile)) {
            pw.println("Task Category,Day of Week,Count");
            for (String category : grouped.keySet()) {
                Map<String, Long> dayCounts = grouped.get(category);
                for (String day : dayCounts.keySet()) {
                    pw.printf("%s,%s,%d\n", category, day, dayCounts.get(day));
                }
            }
        }
        System.out.println("Operation 10 completed: " + outputFile);
    }

    public static void operation18(List<Employee> employees, String outputFile) throws IOException {
        // Group employees by their IDs and extract a sorted list of unique dates for each employee
        Map<String, List<LocalDate>> empDateMap = employees.stream()
                .filter(e -> e.date != null)
                .collect(Collectors.groupingBy(Employee::getEmployeeId,
                        Collectors.collectingAndThen(Collectors.mapping(e -> e.date, Collectors.toSet()), // e -> e.date to access the date
                                dates -> dates.stream().sorted().collect(Collectors.toList()))));

        // Map to hold employee IDs and the list of two consecutive zero-hour dates
        Map<String, List<LocalDate>> emp3Dates = new HashMap<>();

        // Iterate through the employee date map and check for the condition of 3+ consecutive zero-hour days
        empDateMap.forEach((empId, dates) -> {
            int consecutiveCount = 1;
            boolean flag = false;

            // Iterate through the sorted dates for the employee
            for (int i = 1; i < dates.size(); i++) {
                // Check if the difference between the current date and the previous one is at least 3 days
                if (ChronoUnit.DAYS.between(dates.get(i - 1), dates.get(i)) >= 3) {
                    flag = true;
                    if (flag) {
                        // Add the two dates where the gap is at least 3 days
                        emp3Dates.putIfAbsent(empId, List.of(dates.get(i - 1), dates.get(i)));
                        break;
                    }
                } else {
                    flag = false;
                }
            }
        });

        // Prepare the list of employees who have 3+ consecutive zero-hour days
        List<String> empWith3ConsecZeros = new ArrayList<>(emp3Dates.keySet());

        // Write the result to the output file
        try (PrintWriter pw = new PrintWriter(outputFile)) {
            pw.println("Employee ID");
            for (String empId : empWith3ConsecZeros) {
                pw.println(empId);
            }
        }

        System.out.println("Operation 18 completed: " + outputFile);
    }



    // Operation 19: Consistency analysis per task category (least variance in hours)
    public static void operation19(List<Employee> employees, String outputFile) throws IOException {
        Map<String, List<Double>> categoryHours = employees.stream()
                .collect(Collectors.groupingBy(e -> e.taskCategory,
                        Collectors.mapping(e -> e.hoursWorked, Collectors.toList())));

        Map<String, Double> categoryVariance = new LinkedHashMap<>();

        for (Map.Entry<String, List<Double>> entry : categoryHours.entrySet()) {
            List<Double> hoursList = entry.getValue();
            double avg = hoursList.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            double variance = hoursList.stream()
                    .mapToDouble(h -> (h - avg) * (h - avg))
                    .average()
                    .orElse(0.0);
            categoryVariance.put(entry.getKey(), variance);
        }

        LinkedHashMap<String, Double> sorted = categoryVariance.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));

        try (PrintWriter pw = new PrintWriter(outputFile)) {
            pw.println("Task Category,Variance in Hours Worked");
            for (Map.Entry<String, Double> entry : sorted.entrySet()) {
                pw.printf("%s,%.4f\n", entry.getKey(), entry.getValue());
            }
        }
        System.out.println("Operation 19 completed: " + outputFile);
    }


}
