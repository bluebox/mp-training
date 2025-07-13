package dev.tulasidhar.case_study_2_excelfilereading.util;

import dev.tulasidhar.case_study_2_excelfilereading.model.EmployeeWorkLog;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelWriter {
    public static void writeWorkLogsToExcel(List<EmployeeWorkLog> logs, String filePath) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("EmployeeWorkLogs");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Employee ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Department");
        header.createCell(3).setCellValue("Project ID");
        header.createCell(4).setCellValue("Date");
        header.createCell(5).setCellValue("Task Category");
        header.createCell(6).setCellValue("Hours Worked");
        header.createCell(7).setCellValue("Remarks");

        int rowNum = 1;
        for (EmployeeWorkLog log : logs) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(log.getEmployeeId());
            row.createCell(1).setCellValue(log.getName());
            row.createCell(2).setCellValue(log.getDepartment());
            row.createCell(3).setCellValue(log.getProjectId());
            row.createCell(4).setCellValue(log.getDateTime().toString());
            row.createCell(5).setCellValue(log.getTaskCategory());
            row.createCell(6).setCellValue(log.getHoursWorked());
            row.createCell(7).setCellValue(log.getRemarks());
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
        workbook.close();
    }
}
