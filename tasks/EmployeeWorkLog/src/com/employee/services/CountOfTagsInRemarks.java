package com.employee.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.domain.EmployeeWorkLog;

public class CountOfTagsInRemarks {

	public void writeTagCounts(List<EmployeeWorkLog> logs, String filePath) throws IOException {
    	
    	Map<String, Long> tagCounts = logs.stream()
                .map(EmployeeWorkLog::getRemarks)
                .filter(Objects::nonNull)
                .map(String::toLowerCase)
                .map(String::trim)
                .filter(remark -> !remark.isEmpty())
                .collect(Collectors.groupingBy(remark -> remark, Collectors.counting()));
    	
        	Workbook workbook = new XSSFWorkbook();
          
            Sheet sheet = workbook.createSheet("Tags Summary");

            Row header = sheet.createRow(0);
            Cell tagHeader = header.createCell(0);
            tagHeader.setCellValue("Tag");
          
            Cell countHeader = header.createCell(1);
            countHeader.setCellValue("Count");


            int rowIndex = 1;
            for (Map.Entry<String, Long> entry : tagCounts.entrySet()) {
                String tag = entry.getKey();
                Long count = entry.getValue();

                if (tag == null || count == null) {
                	continue;
                }

                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(tag);
                row.createCell(1).setCellValue(count);
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

            workbook.close();
    }
    
}
