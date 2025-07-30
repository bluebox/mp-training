package EmployeeProductivity;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingExcel {
	/*public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("C:\\Users\\DELL\\Downloads\\data.xlsx"); 

        Workbook workbook = WorkbookFactory.create(fis); 
        Sheet sheet = workbook.getSheetAt(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            String empId = getStringCellValue(row.getCell(0));
            String name = getStringCellValue(row.getCell(1));
            String dept = getStringCellValue(row.getCell(2));
            String projectId = getStringCellValue(row.getCell(3));

            String dateStr = "";
            Cell dateCell = row.getCell(4);
            if (dateCell != null && dateCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                if (DateUtil.isCellDateFormatted(dateCell)) {
                    Date date = dateCell.getDateCellValue();
                    dateStr = sdf.format(date);
                } else {
                    Date date = DateUtil.getJavaDate(dateCell.getNumericCellValue());
                    dateStr = sdf.format(date);
                }
            }

            String taskCategory = getStringCellValue(row.getCell(5));

            double hours = 0.0;
            Cell hoursCell = row.getCell(6);
            if (hoursCell != null && hoursCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                hours = hoursCell.getNumericCellValue();
            }

            String remarks = getStringCellValue(row.getCell(7));

            System.out.println(empId + " | " + name + " | " + dept + " | " + projectId + " | " +  dateStr + " | " + taskCategory + " | " + hours + " | " + remarks);
        }

        fis.close();
    }*/
	public static List<EmployeeWorkLog> readExcel(String filePath) {
        List<EmployeeWorkLog> logs = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String empId = getCellString(row.getCell(0));
                String name = getCellString(row.getCell(1));
                String dept = getCellString(row.getCell(2));
                String projectId = getCellString(row.getCell(3));

                LocalDate date = null;
                Cell dateCell = row.getCell(4);
                if (dateCell != null && dateCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    Date javaDate = DateUtil.getJavaDate(dateCell.getNumericCellValue());
                    date = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }

                String task = getCellString(row.getCell(5));

                double hours = 0.0;
                Cell hoursCell = row.getCell(6);
                if (hoursCell != null && hoursCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    hours = hoursCell.getNumericCellValue();
                }

                String remarks = getCellString(row.getCell(7));

                logs.add(new EmployeeWorkLog(empId, name, dept, projectId, date, task, hours, remarks));
            }

        } catch (Exception e) {
            System.err.println("Error reading Excel: " + e.getMessage());
        }

        return logs;
    }

    private static String getCellString(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == Cell.CELL_TYPE_STRING)
            return cell.getStringCellValue();
        else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
            return String.valueOf(cell.getNumericCellValue());
        else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
            return String.valueOf(cell.getBooleanCellValue());
        else
            return "";
    }

}
