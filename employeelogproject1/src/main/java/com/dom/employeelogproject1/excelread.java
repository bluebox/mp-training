package com.dom.employeelogproject1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class excelread {

    private static final String FILE_PATH = "D:\\practice\\employee_productivity\\employeeeedata.xlsx";

    public static List<EmployeeLog> readExcelFile() {
        List<EmployeeLog> logs = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Sheet1");
            DataFormatter formatter = new DataFormatter();
            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue; 
                }

                String employeeId = formatter.formatCellValue(row.getCell(0)).trim();
                String name = formatter.formatCellValue(row.getCell(1)).trim();
                String department = formatter.formatCellValue(row.getCell(2)).trim();
                String projectId = formatter.formatCellValue(row.getCell(3)).trim();

                Cell dateCell = row.getCell(4);
                LocalDate date;
                if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
                    date = dateCell.getLocalDateTimeCellValue().toLocalDate();
                } else {
                    String dateStr = formatter.formatCellValue(dateCell).trim();
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yy");
                    date = LocalDate.parse(dateStr, dateFormatter);
                }

                String taskCategory = formatter.formatCellValue(row.getCell(5)).trim();
                String hoursWorkedStr = formatter.formatCellValue(row.getCell(6)).trim();
                double hoursWorked = hoursWorkedStr.isEmpty() ? 0.0 : Double.parseDouble(hoursWorkedStr);
                String remarks = formatter.formatCellValue(row.getCell(7)).trim();

                EmployeeLog log = new EmployeeLog(
                        date, department, employeeId, hoursWorked,
                        name, projectId, remarks, taskCategory
                );

                logs.add(log);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logs;
    }

    public static void main(String[] args) {
        List<EmployeeLog> logs = readExcelFile();
        for (EmployeeLog log : logs) {
            System.out.println(log);
        }
    }
}
