package casestudy2;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

                String id = getCellAsString(row.getCell(0));
                String name = getCellAsString(row.getCell(1));
                String dept = getCellAsString(row.getCell(2));
                String proj = getCellAsString(row.getCell(3));

                LocalDate date = parseDateCell(row.getCell(4)); // Updated logic here

                String category = getCellAsString(row.getCell(5));
                double hours = row.getCell(6).getNumericCellValue();
                String remarks = getCellAsString(row.getCell(7));

                logs.add(new EmployeeWorkLog(id, name, dept, proj, date, category, hours, remarks));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logs;
    }

    private String getCellAsString(Cell cell) {
        if (cell == null) return "";
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    private LocalDate parseDateCell(Cell cell) {
        if (cell == null) throw new IllegalStateException("Date cell is null");

        if (cell.getCellType() == CellType.STRING) {
            return LocalDate.parse(cell.getStringCellValue().trim(), DATE_FORMATTER);
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        } else {
            throw new IllegalStateException("Unsupported date cell type: " + cell.getCellType());
        }
    }
}
