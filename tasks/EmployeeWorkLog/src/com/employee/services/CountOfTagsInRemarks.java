package com.employee.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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
    	
    	List<String> headers = Arrays.asList("Tag in Remarks","count");
    	
        ExcelWriter excelWriter  = new ExcelWriter();
    	Workbook workbook = excelWriter.createWorkBook(headers);
    	Sheet sheet = workbook.getSheetAt(0);


            AtomicInteger rowIndex = new AtomicInteger(1);
            for (Map.Entry<String, Long> entry : tagCounts.entrySet()) {
                String tag = entry.getKey();
                Long count = entry.getValue();

                if (tag == null || count == null) {
                	continue;
                }

                Row row = sheet.createRow(rowIndex.getAndIncrement());
                row.createCell(0).setCellValue(tag);
                row.createCell(1).setCellValue(count);
            }

            excelWriter.writeToExcel(workbook, filePath);
            workbook.close();
    }
    
}