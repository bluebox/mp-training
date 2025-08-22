package com.prana;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public static List<EmpWorkLog> readWorkLogs(String filePath) {
        List<EmpWorkLog> logs = new ArrayList<>();
        try (InputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; 
                String empId = getStringCell(row.getCell(0));
                String name = getStringCell(row.getCell(1));
                String dept = getStringCell(row.getCell(2));
                String projectId = getStringCell(row.getCell(3));
                LocalDate date = null;
                Cell dateCell = row.getCell(4);
                if (dateCell != null) {
                    if (dateCell.getCellType() == CellType.NUMERIC) {
                        date = dateCell.getDateCellValue().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate();
                    } else if (dateCell.getCellType() == CellType.STRING) {
                        try {
                            date = LocalDate.parse(dateCell.getStringCellValue(),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        } catch (Exception ex) {
                            System.out.println("Bad date format in row " + row.getRowNum());
                        }    }   }

                String category = getStringCell(row.getCell(5));
                double hours = 0.0;
                Cell hoursCell = row.getCell(6);
                if (hoursCell != null) {
                    if (hoursCell.getCellType() == CellType.NUMERIC) {
                        hours = hoursCell.getNumericCellValue();
                    } else if (hoursCell.getCellType() == CellType.STRING) {
                        try {
                           hours = Double.parseDouble(hoursCell.getStringCellValue());
                  } catch (Exception ex) {
                            System.out.println("Bad hours format in row " + row.getRowNum());
                        }   }  }
                String remarks = getStringCell(row.getCell(7));
                logs.add(new EmpWorkLog(empId, name, dept, projectId, date, category, hours, remarks));
          }
        } catch (Exception e) {
            e.printStackTrace();     }
   return logs;
   }
    private static String getStringCell(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
        if (cell.getCellType() == CellType.NUMERIC) return String.valueOf(cell.getNumericCellValue());
        return "";
   }
}
