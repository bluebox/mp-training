package study;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.*;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    public static List<EmployeeWorkLog> readExcel(String filePath) throws Exception {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        FileInputStream fis = new FileInputStream("C://Users/rohith reddy/Dropbox/My PC (LAPTOP-I2UV09L7)/Desktop/Case Study-2/input_data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
          XSSFSheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            
            String empId = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();
            String dept = row.getCell(2).getStringCellValue();
            String projId = row.getCell(3).getStringCellValue();

            LocalDate date;
            Cell dateCell = row.getCell(4);
            if (DateUtil.isCellDateFormatted(dateCell)) {
                date = dateCell.getDateCellValue().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            } else {
                date = dateCell.getLocalDateTimeCellValue().toLocalDate();
            }
            
            String category = row.getCell(5).getStringCellValue();
            double hours = row.getCell(6).getNumericCellValue();
            String remarks = row.getCell(7).getStringCellValue();

            logs.add(new EmployeeWorkLog(empId, name, dept, projId, date, category, hours, remarks));
        }

        workbook.close();
        fis.close();
        return logs;
    }
}