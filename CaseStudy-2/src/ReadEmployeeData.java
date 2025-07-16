package com.employee.dao;

import com.employee.model.EmployeeWorkLog;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadEmployeeData {
    public List<EmployeeWorkLog> readEmployeeData(String filePath) {
        List<EmployeeWorkLog> workLogs = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0 || row == null) continue;

                try {
                    String employeeId = getCellValue(row.getCell(0));
                    String name = getCellValue(row.getCell(1));
                    String department = getCellValue(row.getCell(2));
                    String projectId = getCellValue(row.getCell(3));
                    LocalDate date = getDate(row.getCell(4));
                    String taskCategory = getCellValue(row.getCell(5));
                    double hoursWorked = row.getCell(6).getNumericCellValue();
                    String remarks = getCellValue(row.getCell(7));
                    LocalTime time = row.getCell(8) != null ? LocalTime.parse(getCellValue(row.getCell(8))) : null;

                    workLogs.add(new EmployeeWorkLog(employeeId, name, department, projectId, date,
                            taskCategory, hoursWorked, remarks, time));
                } catch (Exception e) {
                    System.err.println("Skipping row " + row.getRowNum() + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return workLogs;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    private LocalDate getDate(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            Date date = cell.getDateCellValue();
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return LocalDate.parse(getCellValue(cell));
    }
}
