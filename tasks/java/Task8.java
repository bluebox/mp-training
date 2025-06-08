import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

public class Task8 {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "C:\\Users\\mourya\\OneDrive\\Desktop\\Sample_Employee_WorkLogs.xlsx";
        String outputFilePath = "C:\\Users\\mourya\\OneDrive\\Desktop\\GroupedAndWeeklyEffort.xlsx";

        ArrayList<EmployeeWorkLog> employees = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean skipHeader = true;

            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                EmployeeWorkLog emp = new EmployeeWorkLog();
                emp.setEmployeeId(getCellAsString(row.getCell(0)));
                emp.setName(getCellAsString(row.getCell(1)));
                emp.setDepartment(getCellAsString(row.getCell(2)));
                emp.setProjectId(getCellAsString(row.getCell(3)));

                Cell dateCell = row.getCell(4);
                if (dateCell != null) {
                    if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
                        emp.setDate(dateCell.getDateCellValue().toInstant()
                                .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
                    } else if (dateCell.getCellType() == CellType.STRING) {
                        emp.setDate(LocalDate.parse(dateCell.getStringCellValue()));
                    }
                }

                emp.setTaskCategory(getCellAsString(row.getCell(5)));
                Cell hoursCell = row.getCell(6);
                if (hoursCell != null && hoursCell.getCellType() == CellType.NUMERIC) {
                    emp.setHoursWorked(hoursCell.getNumericCellValue());
                }
                emp.setRemarks(getCellAsString(row.getCell(7)));

                employees.add(emp);
            }
        }

        Workbook outputWorkbook = new XSSFWorkbook();


        Map<String, Map<LocalDate, Double>> weeklyEffort = employees.stream()
                .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId,
                        Collectors.groupingBy(e -> e.getDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
                                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))));

        Sheet weeklySheet = outputWorkbook.createSheet("WeeklyEffortPerProject");
        int rowNum = 0;
        Row headerRow = weeklySheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Project ID");
        headerRow.createCell(1).setCellValue("Week Start Date (Monday)");
        headerRow.createCell(2).setCellValue("Total Hours Worked");

        for (Map.Entry<String, Map<LocalDate, Double>> projectEntry : weeklyEffort.entrySet()) {
            String projectId = projectEntry.getKey();
            for (Map.Entry<LocalDate, Double> weekEntry : projectEntry.getValue().entrySet()) {
                Row row = weeklySheet.createRow(rowNum++);
                row.createCell(0).setCellValue(projectId);
                row.createCell(1).setCellValue(weekEntry.getKey().toString());
                row.createCell(2).setCellValue(weekEntry.getValue());
            }
        }

        try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
            outputWorkbook.write(fos);
        }

        System.out.println("Weekly effort per project written to new Excel sheet successfully.");
    }

    private static String getCellAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}

