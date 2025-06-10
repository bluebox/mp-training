package com.medplus.casestudies.employeeproject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelReader {
    public List<EmployeeWorkLog> readWorkLogs(String filePath) {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String empId = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String department = row.getCell(2).getStringCellValue();
                String projectId = row.getCell(3).getStringCellValue();

                Cell dateCell = row.getCell(4);
                LocalDate date;
                if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
                    Date javaDate = dateCell.getDateCellValue();
                    date = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                } else {
                    String dateStr = dateCell.getStringCellValue();
                    Date javaDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                    date = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }

                String taskCategory = row.getCell(5).getStringCellValue();
                double hoursWorked = row.getCell(6).getNumericCellValue();
                String remarks = row.getCell(7).getStringCellValue();

                logs.add(new EmployeeWorkLog(empId, name, department, projectId, date, taskCategory, hoursWorked, remarks));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logs;
    }
}