package com.service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.domain.EmployeePojo;

public class DataLoder {
	
	public  void dataLoder(EmployeeDataAnalytics employeeDataAnalytics,EmployeePojo employeePojo)
	{
		 String filePath = ".\\excelfiles\\Sample_Employee_WorkLogs.xlsx";

	        try (FileInputStream inputStream = new FileInputStream(filePath);
	             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

	            XSSFSheet sheet = workbook.getSheetAt(0);

	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	                XSSFRow row = sheet.getRow(i);
	                if (row == null) continue;

	                String employeeId = row.getCell(0).getStringCellValue();
	                String name = row.getCell(1).getStringCellValue();
	                String department = row.getCell(2).getStringCellValue();
	                String projectId = row.getCell(3).getStringCellValue();

	                // ✅ Correct Date Handling
	                Cell dateCell = row.getCell(4);
	                java.sql.Date sqlDate = null;

	                if (dateCell != null) {
	                    if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
	                        Date utilDate = dateCell.getDateCellValue();
	                        sqlDate = new java.sql.Date(utilDate.getTime()); 
	                    } else if (dateCell.getCellType() == CellType.STRING) {
	                        String dateStr = dateCell.getStringCellValue();
	                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                        Date utilDate = sdf.parse(dateStr);
	                        sqlDate = new java.sql.Date(utilDate.getTime());
	                    }
	                }

	                String task = row.getCell(5).getStringCellValue();
	                double hoursWorked = row.getCell(6).getNumericCellValue();
	                String remarks = row.getCell(7).getStringCellValue();

	                employeePojo = new EmployeePojo(employeeId, name, department, projectId,
	                        sqlDate, task, hoursWorked, remarks);

	                employeeDataAnalytics.setEmployeePojo(employeePojo);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
