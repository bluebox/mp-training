package com.medplus.casestudies.employeeproject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ExcelWriter {

    public void writeWeeklyAverages(Map<String, Double> data, String path) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Weekly Avg Hours");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Employee ID");
            header.createCell(1).setCellValue("Avg Weekly Hours");

            int rowIdx = 1;
            for (Map.Entry<String, Double> entry : data.entrySet()) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }

            try (FileOutputStream out = new FileOutputStream(path)) {
                workbook.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDailyTrend(Map<String, Map<LocalDate, Double>> trend) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Daily Trend");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Employee ID");
            header.createCell(1).setCellValue("Date");
            header.createCell(2).setCellValue("Avg Hours");

            int rowIdx = 1;
            for (Map.Entry<String, Map<LocalDate, Double>> empEntry : trend.entrySet()) {
                for (Map.Entry<LocalDate, Double> dateEntry : empEntry.getValue().entrySet()) {
                    Row row = sheet.createRow(rowIdx++);
                    row.createCell(0).setCellValue(empEntry.getKey());
                    row.createCell(1).setCellValue(dateEntry.getKey().toString());
                    row.createCell(2).setCellValue(dateEntry.getValue());
                }
            }

            try (FileOutputStream out = new FileOutputStream("src/main/java/com/medplus/casestudies/employeeproject/Daily_Trend.xlsx")) {
                workbook.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeZeroHourStreaks(Map<String, List<LocalDate>> data) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Zero Hour Streaks");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Employee ID");
            header.createCell(1).setCellValue("Zero-Hour Dates");

            int rowIdx = 1;
            for (Map.Entry<String, List<LocalDate>> entry : data.entrySet()) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue().toString());
            }

            try (FileOutputStream out = new FileOutputStream("src/main/java/com/medplus/casestudies/employeeproject/Zero_Hour_Streaks.xlsx")) {
                workbook.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLowHourDays(Map<String, Map<LocalDate, Double>> data) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Low Hour Days");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Employee ID");
            header.createCell(1).setCellValue("Date");
            header.createCell(2).setCellValue("Hours Worked");

            int rowIdx = 1;
            for (Map.Entry<String, Map<LocalDate, Double>> empEntry : data.entrySet()) {
                for (Map.Entry<LocalDate, Double> dateEntry : empEntry.getValue().entrySet()) {
                    Row row = sheet.createRow(rowIdx++);
                    row.createCell(0).setCellValue(empEntry.getKey());
                    row.createCell(1).setCellValue(dateEntry.getKey().toString());
                    row.createCell(2).setCellValue(dateEntry.getValue());
                }
            }

            try (FileOutputStream out = new FileOutputStream("src/main/java/com/medplus/casestudies/employeeproject/Low_Hours_Output.xlsx")) {
                workbook.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeProjectHoppers(Map<String, Map<String, Long>> data) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Project Hoppers");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Employee ID");
            header.createCell(1).setCellValue("Month-Year");
            header.createCell(2).setCellValue("Distinct Projects");

            int rowIdx = 1;
            for (Map.Entry<String, Map<String, Long>> empEntry : data.entrySet()) {
                for (Map.Entry<String, Long> entry : empEntry.getValue().entrySet()) {
                    Row row = sheet.createRow(rowIdx++);
                    row.createCell(0).setCellValue(empEntry.getKey());
                    row.createCell(1).setCellValue(entry.getKey());
                    row.createCell(2).setCellValue(entry.getValue());
                }
            }

            try (FileOutputStream out = new FileOutputStream("src/main/java/com/medplus/casestudies/employeeproject/Project_Hoppers.xlsx")) {
                workbook.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
