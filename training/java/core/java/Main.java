import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = "/home/shyam/Desktop/java_files/poi-excel-demo/src/main/java/Sample_Employee_WorkLogs.xlsx";
        String outputFilePath = "/home/shyam/Desktop/java_files/poi-excel-demo/src/main/java/Filtered_WorkLogs.xlsx";

        List<List<Object>> filteredRows = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (FileInputStream fis = new FileInputStream(new File(inputFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();

            Map<String, List<List<Object>>> groupedByEmpDate = new LinkedHashMap<>();

            for (int i = 1; i < rowCount; i++) { // Skip header
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String empId = getCellString(row.getCell(0));
                String name = getCellString(row.getCell(1));
                String department = getCellString(row.getCell(2));
                String projectId = getCellString(row.getCell(3));
                Date date = getCellDate(row.getCell(4));
                String taskCategory = getCellString(row.getCell(5));
                double hours = getCellDouble(row.getCell(6));
                String remarks = getCellString(row.getCell(7));

                if (hours < 2) {
                    List<Object> rowData = Arrays.asList(empId, name, department, projectId, date, taskCategory, hours, remarks);

                    String key = empId + "_" + sdf.format(date);
                    groupedByEmpDate.computeIfAbsent(key, k -> new ArrayList<>()).add(rowData);
                }
            }

            // Flatten grouped results
            for (List<List<Object>> rows : groupedByEmpDate.values()) {
                filteredRows.addAll(rows);
            }

            writeToExcel(filteredRows, outputFilePath);
            System.out.println("Filtered records written to: " + outputFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getCellString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
        case STRING:
            return cell.getStringCellValue();
        case NUMERIC:
            return String.valueOf(cell.getNumericCellValue());
        case BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());
        default:
            return "";
    }

    }

    private static double getCellDouble(Cell cell) {
        if (cell == null) return 0;
        if (cell.getCellType() == CellType.NUMERIC) return cell.getNumericCellValue();
        if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    private static Date getCellDate(Cell cell) {
        if (cell == null) return null;

        if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            }
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                // Try to parse date manually if it was stored as text
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse(cell.getStringCellValue());
            } catch (Exception e) {
                // Ignore parse errors; return null
            }
        }

        return null; // fallback
    }


    private static void writeToExcel(List<List<Object>> data, String outputPath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Filtered Logs");

            // Header
            String[] headers = {"Employee ID", "Name", "Department", "Project ID", "Date", "Task Category", "Hours Worked", "Remarks"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                header.createCell(i).setCellValue(headers[i]);
            }

            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

            int rowIndex = 1;
            for (List<Object> rowData : data) {
                Row row = sheet.createRow(rowIndex++);
                for (int i = 0; i < rowData.size(); i++) {
                    Cell cell = row.createCell(i);
                    Object value = rowData.get(i);
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Double) {
                        cell.setCellValue((Double) value);
                    } else if (value instanceof Date) {
                        cell.setCellValue((Date) value);
                        cell.setCellStyle(dateStyle);
                    }
                }
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

