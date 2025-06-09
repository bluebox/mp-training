package com.service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xddf.usermodel.text.CapsType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.domain.EmployeePojo;

public class DataLoder {
	
	public  void dataLoder(EmployeeDataAnalytics employeeDataAnalytics,EmployeePojo employeePojo)
	{
		 String filePath = "/home/mphs/Desktop/mp-training/tasks/AnalyticalCase/src/Sample_Employee_WorkLogs.xlsx";

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

	                XSSFCell dateCell = row.getCell(4);
	                LocalDate date = null;

	                if (dateCell != null) {
	                    if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
	                         date = LocalDate.parse((CharSequence) dateCell.getDateCellValue());
	                    }
	                    else if (dateCell.getCellType() == CellType.STRING) {
	                    	date = LocalDate.parse( dateCell.getStringCellValue());
	                  
	                    }
	                }

	                String task = row.getCell(5).getStringCellValue();
	                double hoursWorked = row.getCell(6).getNumericCellValue();
	                String remarks = row.getCell(7).getStringCellValue();

	                employeePojo = new EmployeePojo(employeeId, name, department, projectId,
	                        date, task, hoursWorked, remarks);

	                employeeDataAnalytics.setEmployeePojo(employeePojo);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
