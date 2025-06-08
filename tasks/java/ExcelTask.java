import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelTask {
    public static void main(String[] args) throws IOException {
        String inputPath = "C:\\Users\\mourya\\OneDrive\\Desktop\\Sample_Employee_WorkLogs.xlsx";
        String outputPath = "C:\\Users\\mourya\\OneDrive\\Desktop\\Grouped_Employee_WorkLogs.xlsx";

        ArrayList<EmployeeWorkLog> employees = new ArrayList<>();


        try (FileInputStream fis = new FileInputStream(inputPath);
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
                        emp.setDate(dateCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
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


        Map<String, Map<String, List<EmployeeWorkLog>>> grouped = employees.stream()
                .collect(Collectors.groupingBy(EmployeeWorkLog::getDepartment,
                        Collectors.groupingBy(EmployeeWorkLog::getProjectId)));

        Workbook outWorkbook = new XSSFWorkbook();
        Sheet outSheet = outWorkbook.createSheet("Grouped Data");

        String[] headers = {"Employee ID", "Name", "Department", "Project ID", "Date", "Task Category", "Hours Worked", "Remarks"};
        Row headerRow = outSheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        int rowIndex = 1;
        for (String department : grouped.keySet()) {
            Map<String, List<EmployeeWorkLog>> projectMap = grouped.get(department);

            List<Map.Entry<String, List<EmployeeWorkLog>>> sortedProjects = projectMap.entrySet().stream()
                    .sorted((e1, e2) -> Double.compare(
                            e2.getValue().stream().mapToDouble(EmployeeWorkLog::getHoursWorked).sum(),
                            e1.getValue().stream().mapToDouble(EmployeeWorkLog::getHoursWorked).sum()
                    )).collect(Collectors.toList());

            for (Map.Entry<String, List<EmployeeWorkLog>> entry : sortedProjects) {
                for (EmployeeWorkLog emp : entry.getValue()) {
                    Row row = outSheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(emp.getEmployeeId());
                    row.createCell(1).setCellValue(emp.getName());
                    row.createCell(2).setCellValue(emp.getDepartment());
                    row.createCell(3).setCellValue(emp.getProjectId());
                    row.createCell(4).setCellValue(emp.getDate().toString());
                    row.createCell(5).setCellValue(emp.getTaskCategory());
                    row.createCell(6).setCellValue(emp.getHoursWorked());
                    row.createCell(7).setCellValue(emp.getRemarks());
                }
            }
        }

        for (int i = 0; i < headers.length; i++) {
            outSheet.autoSizeColumn(i);
        }

        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            outWorkbook.write(fos);
            outWorkbook.close();
        }

        System.out.println("✅ Grouped and sorted employee work logs written to: " + outputPath);
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
