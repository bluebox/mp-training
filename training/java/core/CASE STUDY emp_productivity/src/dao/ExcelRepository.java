package dao;

import model.EmployeeWorkLog;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExcelRepository {

    public List<EmployeeWorkLog> readFromExcel(String filePath) {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String empId = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String dept = row.getCell(2).getStringCellValue();
                String projId = row.getCell(3).getStringCellValue();
                LocalDate date = row.getCell(4).getLocalDateTimeCellValue().toLocalDate();
                String category = row.getCell(5).getStringCellValue();
                double hours = row.getCell(6).getNumericCellValue();
                String remarks = row.getCell(7).getStringCellValue();

                logs.add(new EmployeeWorkLog(empId, name, dept, projId, date, category, hours, remarks));
                System.out.println(logs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logs;
    }
}
