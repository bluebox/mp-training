package com.demo.employeeelog;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExcelReader {

    public static List<EmployeeWorkLog> readExcel(String filePath) throws Exception {
        List<EmployeeWorkLog> logs = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            // Skip header row
            if (iterator.hasNext()) iterator.next();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String employeeId = getCellString(row.getCell(0));           // emp_id
                String name = getCellString(row.getCell(1));                 // employee_name
                String department = getCellString(row.getCell(2));           // department
                String projectId = getCellString(row.getCell(3));            // project
                String taskCategory = getCellString(row.getCell(4));         // task_category
                String taskDesc = getCellString(row.getCell(5));             // task_description
                LocalDate date = getCellDate(row.getCell(6));                // date
                double hoursWorked = getCellNumeric(row.getCell(7));         // hours_worked
                String remarks = getCellString(row.getCell(8));              // remarks


                

                EmployeeWorkLog log = new EmployeeWorkLog(
                        employeeId, projectId, department, date, hoursWorked, taskCategory
                );
                logs.add(log);
            }
        }

        return logs;
    }

    private static String getCellString(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
        else if (cell.getCellType() == CellType.NUMERIC) return String.valueOf(cell.getNumericCellValue());
        else return "";
    }

    private static double getCellNumeric(Cell cell) {
        if (cell == null) return 0;
        if (cell.getCellType() == CellType.NUMERIC) return cell.getNumericCellValue();
        else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue().trim());
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    private static LocalDate getCellDate(Cell cell) {
        if (cell == null) return null;

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else if (cell.getCellType() == CellType.STRING) {
            String dateStr = cell.getStringCellValue().trim();
            DateTimeFormatter[] formatters = {
                    DateTimeFormatter.ofPattern("M/d/yy"),     // e.g., 7/14/25
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"), // e.g., 2025-07-14
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")  // e.g., 14/07/2025
            };
            for (DateTimeFormatter formatter : formatters) {
                try {
                    return LocalDate.parse(dateStr, formatter);
                } catch (Exception ignore) { }
            }
        }

        // fallback
        return null;
    }
}



