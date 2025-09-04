package EmpProductivity;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<EmployeeWorkLog> readWorkLogs(String filePath) {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String empId = getSafeString(row.getCell(0));
                String name = getSafeString(row.getCell(1));
                String dept = getSafeString(row.getCell(2));
                String projectId = getSafeString(row.getCell(3));

                LocalDate date = null;
                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC) {
                    date = row.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                } else {
                    System.out.println("Skipping row " + (row.getRowNum() + 1) + " due to invalid or missing date");
                    continue;
                }

                String category = getSafeString(row.getCell(5));

                double hours = 0.0;
                if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.NUMERIC) {
                    hours = row.getCell(6).getNumericCellValue();
                }

                String remarks = getSafeString(row.getCell(7));

                logs.add(new EmployeeWorkLog(empId, name, dept, projectId, date, category, hours, remarks));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return logs;
    }

    private static String getSafeString(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
        if (cell.getCellType() == CellType.NUMERIC) return String.valueOf(cell.getNumericCellValue());
        if (cell.getCellType() == CellType.BOOLEAN) return String.valueOf(cell.getBooleanCellValue());
        return "";
    }
}
