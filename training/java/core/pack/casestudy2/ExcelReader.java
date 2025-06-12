package casestudy2;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<EmployeeWorkLog> readFromExcel(String filePath) {
        List<EmployeeWorkLog> logs = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                try {
                    // Assume null ID indicates end of meaningful data
                    Cell idCell = row.getCell(0);
                    if (idCell == null) {
                        System.out.println("Null ID cell at row " + row.getRowNum() + " — assuming end of data.");
                        break;
                    }

                    String id = getCellAsString(idCell);
                    String name = getCellAsString(row.getCell(1));
                    String dept = getCellAsString(row.getCell(2));
                    String proj = getCellAsString(row.getCell(3));
                    String category = getCellAsString(row.getCell(5));
                    String remarks = getCellAsString(row.getCell(7));

                    // Check for empty strings
                    if (id.isEmpty() || name.isEmpty() || dept.isEmpty() || proj.isEmpty()
                            || category.isEmpty() || remarks.isEmpty()) {
                        System.out.println("Empty string fields at row " + row.getRowNum() + " — skipping.");
                        continue;
                    }

                    // Parse date
                    LocalDate date = parseDateCell(row.getCell(4));
                    if (date == null) {
                        System.out.println("Invalid or missing date at row " + row.getRowNum() + " — skipping.");
                        continue;
                    }

                    // Parse hours
                    Cell hoursCell = row.getCell(6);
                    if (hoursCell == null || hoursCell.getCellType() == CellType.BLANK) {
                        System.out.println("Missing hours at row " + row.getRowNum() + " — skipping.");
                        continue;
                    }

                    double hours;
                    if (hoursCell.getCellType() == CellType.STRING) {
                        String hoursStr = hoursCell.getStringCellValue().trim();
                        if (hoursStr.isEmpty()) {
                            System.out.println("Empty hours string at row " + row.getRowNum() + " — skipping.");
                            continue;
                        }
                        hours = Double.parseDouble(hoursStr);
                    } else {
                        hours = hoursCell.getNumericCellValue();
                    }

                    logs.add(new EmployeeWorkLog(id, name, dept, proj, date, category, hours, remarks));

                } catch (IllegalStateException | DateTimeParseException | NullPointerException | NumberFormatException ex) {
                    System.out.println("Skipping row " + row.getRowNum() + " due to error: " + ex.getMessage());
                    
                    // ex.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to read Excel file: " + e.getMessage());
            e.printStackTrace();
        }

        return logs;
    }

    private String getCellAsString(Cell cell) {
        if (cell == null) throw new IllegalStateException("Cell is null");

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

    private LocalDate parseDateCell(Cell cell) {
        if (cell == null) throw new IllegalStateException("Date cell is null");

        return switch (cell.getCellType()) {
            case STRING -> LocalDate.parse(cell.getStringCellValue().trim(), DATE_FORMATTER);
            case NUMERIC -> cell.getLocalDateTimeCellValue().toLocalDate();
            default -> throw new IllegalStateException("Unsupported date cell type: " + cell.getCellType());
        };
    }
}
