import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    public static List<EmployeeWorkLog> readLogs(String filePath) {
        List<EmployeeWorkLog> employees= new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))){
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                try {
                    String employeeId = getCellValue(row.getCell(0));
                    String name = getCellValue(row.getCell(1));
                    String department = getCellValue(row.getCell(2));
                    String projectId = getCellValue(row.getCell(3));
                    Date date = row.getCell(4).getDateCellValue();
                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    String taskCategory = getCellValue(row.getCell(5));
                    double hoursWorked = row.getCell(6) != null ? row.getCell(6).getNumericCellValue() : 0;
                    String remarks = getCellValue(row.getCell(7));
                    EmployeeWorkLog emp = new EmployeeWorkLog(employeeId, name, department, projectId,
                            localDate, taskCategory, hoursWorked, remarks);
                    employees.add(emp);
                } 
                catch (Exception e) {
                    System.out.println("Error occured ");
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return employees;
    }
    private static String getCellValue(Cell cell) {
        return (cell != null) ? cell.getStringCellValue() : "";
    }
}
