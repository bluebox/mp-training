
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*; // Excel cell handling
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // For .xlsx files
import com.opencsv.CSVWriter; // For CSV export





public class WorkLogAnalyzer {
    private List<EmployeeWorkLog> workLogs;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");



    public WorkLogAnalyzer(String excelFilePath) throws IOException, InvalidFormatException {
        this.workLogs = readExcelFile(excelFilePath);
    }

    private List<EmployeeWorkLog> readExcelFile(String filePath) throws IOException, InvalidFormatException {
        List<EmployeeWorkLog> logs = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(new File(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                EmployeeWorkLog log = new EmployeeWorkLog(
                        getStringCellValue(row.getCell(0)), // employeeId
                        getStringCellValue(row.getCell(1)), // name
                        getStringCellValue(row.getCell(2)), // department
                        getStringCellValue(row.getCell(3)), // projectId
                        getDateCellValue(row.getCell(4)),  // date
                        getStringCellValue(row.getCell(5)), // taskCategory
                        getNumericCellValue(row.getCell(6)), // hoursWorked
                        getStringCellValue(row.getCell(7))  // remarks
                );
                logs.add(log);
            }
        }
        return logs;
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        }
        return "";
    }

    private LocalDate getDateCellValue(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue().toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return LocalDate.parse(cell.getStringCellValue().trim(), DATE_FORMATTER);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private double getNumericCellValue(Cell cell) {
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

    public void exportToCSV(Map<?, ?> data, String outputPath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {
            // Write header
            writer.writeNext(new String[]{"Key", "Value"});

            // Write data
            for (Map.Entry<?, ?> entry : data.entrySet()) {
                writer.writeNext(new String[]{
                        entry.getKey().toString(),
                        entry.getValue().toString()
                });
            }
        }
    }

    public void exportComplexToCSV(Map<?, ? extends Map<?, ?>> data, String outputPath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {
            // Determine all unique inner keys for header
            Set<Object> innerKeys = new HashSet<>();
            for (Map<?, ?> innerMap : data.values()) {
                innerKeys.addAll(innerMap.keySet());
            }

            // Write header
            String[] header = new String[innerKeys.size() + 1];
            header[0] = "Key";
            int i = 1;
            for (Object key : innerKeys) {
                header[i++] = key.toString();
            }
            writer.writeNext(header);

            // Write data
            for (Map.Entry<?, ? extends Map<?, ?>> entry : data.entrySet()) {
                String[] row = new String[innerKeys.size() + 1];
                row[0] = entry.getKey().toString();

                i = 1;
                for (Object innerKey : innerKeys) {
                    Map<?, ?> innerMap = entry.getValue();
                    Object value = innerMap.get(innerKey);
                    row[i++] = value != null ? value.toString() : "";
                }

                writer.writeNext(row);
            }
        }
    }

    // Task 31: Days with <2 hours; group by employee and date
    public Map<String, Map<LocalDate, Double>> getDaysWithLessThan2Hours() {
        return workLogs.stream()
                .filter(log -> log.getHoursWorked() < 2)
                .collect(Collectors.groupingBy(
                        EmployeeWorkLog::getEmployeeId,
                        Collectors.groupingBy(
                                EmployeeWorkLog::getDate,
                                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
                        )));
    }

    // Task 9: Top 5 employees with highest hours in last 60 days

    public Map<String, Double> getTop5EmployeesLast60Days() {
        LocalDate cutoffDate = LocalDate.now().minusDays(60);

        Map<String, Double> totalHoursPerEmployee = workLogs.stream()
                .filter(log -> log.getDate() != null && log.getDate().isAfter(cutoffDate))
                .collect(Collectors.groupingBy(
                        log -> log.getEmployeeId() + " - " + log.getName(),
                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
                ));

        return totalHoursPerEmployee.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }


    // Task 18: Find employees with 3+ zero-hour consecutive days
    public Set<String> getEmployeesWith3ConsecutiveZeroDays() {
        Map<String, List<EmployeeWorkLog>> logsByEmployee = workLogs.stream()
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId));

        Set<String> result = new HashSet<>();

        logsByEmployee.forEach((employeeId, logs) -> {
            // Sort logs by date
            List<EmployeeWorkLog> sortedLogs = logs.stream()
                    .filter(log -> log.getDate() != null) // ✅ Filter out null dates
                    .sorted(Comparator.comparing(EmployeeWorkLog::getDate))
                    .collect(Collectors.toList());


            int consecutiveZeroDays = 0;
            LocalDate previousDate = null;

            for (EmployeeWorkLog log : sortedLogs) {
                if (log.getHoursWorked() == 0) {
                    if (previousDate != null &&
                            previousDate.plusDays(1).equals(log.getDate())) {
                        consecutiveZeroDays++;
                    } else {
                        consecutiveZeroDays = 1;
                    }

                    if (consecutiveZeroDays >= 3) {
                        result.add(employeeId);
                        break;
                    }
                } else {
                    consecutiveZeroDays = 0;
                }
                previousDate = log.getDate();
            }
        });

        return result;
    }

    // Task 4: Group by employee and month; compute average weekly hours
    public Map<String, Map<YearMonth, Double>> getAverageWeeklyHoursPerEmployeePerMonth() {
        return workLogs.stream()
                .filter(log -> log.getDate() != null) // ✅ skip null dates
                .collect(Collectors.groupingBy(
                        log -> log.getEmployeeId() + " - " + log.getName(),
                        Collectors.groupingBy(
                                log -> YearMonth.from(log.getDate()),
                                Collectors.collectingAndThen(
                                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked),
                                        totalHours -> totalHours / 4 // Approximate weeks in month
                                )
                        )
                ));

    }

    // Task 26: Weekend logs; summarize weekend hours
    public Map<DayOfWeek, Double> getWeekendHoursSummary() {
        return workLogs.stream()
                .filter(log -> {
                    DayOfWeek day = log.getDate().getDayOfWeek();
                    return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
                })
                .collect(Collectors.groupingBy(
                        log -> log.getDate().getDayOfWeek(),
                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
                ));
    }
}