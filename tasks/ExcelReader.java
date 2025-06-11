import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class ExcelReader {
    public static List<EmployeeWorkLog> readExcelData(String filePath) {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) rowIterator.next(); // Skip header

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                try {
                    String employeeId = row.getCell(0).getStringCellValue();
                    String name = row.getCell(1).getStringCellValue();
                    String department = row.getCell(2).getStringCellValue();
                    String projectId = row.getCell(3).getStringCellValue();
                    LocalDate date = row.getCell(4).getCellType() == CellType.NUMERIC
                            ? row.getCell(4).getLocalDateTimeCellValue().toLocalDate()
                            : LocalDate.parse(row.getCell(4).getStringCellValue(), formatter);
                    String taskCategory = row.getCell(5).getStringCellValue();
                    double hoursWorked = row.getCell(6).getNumericCellValue();
                    Cell remarksCell = row.getCell(7);
                    String remarks = (remarksCell != null && remarksCell.getCellType() == CellType.STRING)
                            ? remarksCell.getStringCellValue() : "";

                    logs.add(new EmployeeWorkLog(employeeId, name, department, projectId, date, taskCategory, hoursWorked, remarks));
                } catch (Exception e) {
                    System.err.println("Skipping row due to error: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return logs;
    }
}
