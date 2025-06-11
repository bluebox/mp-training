package casestudy;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeReader {

    public static List<Employee> readExcel(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // skip header

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                LocalDate date = null;
                Cell dateCell = row.getCell(4);
                if (dateCell != null) {
                    if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
                        date = dateCell.getDateCellValue().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate();
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static String getCellString(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue().trim();
        if (cell.getCellType() == CellType.NUMERIC) return String.valueOf((int) cell.getNumericCellValue());
        return cell.toString().trim();
    }

    private static double getCellDouble(Cell cell) {
        if (cell == null) return 0.0;
        if (cell.getCellType() == CellType.NUMERIC) return cell.getNumericCellValue();
        try {
            return Double.parseDouble(cell.getStringCellValue());
        } catch (Exception e) {
            return 0.0;
        }
    }
}
