package com.employee.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.domain.Employee;

public class DataManager {
	private Map<String, List<Employee>> employees;

	public Map<String, List<Employee>> readEmployeesFromExcel(String filePath) {
		employees = new HashMap<>();
		System.out.println(filePath);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				String id = row.getCell(0).getStringCellValue();
				String name = row.getCell(1).getStringCellValue();
				String department = row.getCell(2).getStringCellValue();
				String projectId = row.getCell(3).getStringCellValue();

				Cell dateCell = row.getCell(4);
				LocalDate date = LocalDate.parse(dateCell.getStringCellValue(), formatter);

				String taskCategory = row.getCell(5).getStringCellValue();
				double hoursWorked = row.getCell(6).getNumericCellValue();
				String remark = row.getCell(7).getStringCellValue();

				Employee emp = new Employee(id, name, department, projectId, date, taskCategory, hoursWorked, remark);

				if (employees.containsKey(id)) {
					employees.get(id).add(emp);

				} else {
					employees.put(id, new ArrayList<Employee>());
					employees.get(id).add(emp);

				}
			}

		} catch (Exception e) {
			System.out.println("File not found Quiting the application");
			System.exit(0);
		}
		return employees;

		// System.out.println(employees);
	}

	public void writeToCSV(String fileName, List<String[]> rows) {
		try (FileWriter writer = new FileWriter(fileName)) {
			for (String[] row : rows) {
				StringBuilder line = new StringBuilder();
				for (int i = 0; i < row.length; i++) {
					line.append(row[i]);

					if (i != row.length - 1) {
						line.append(",");
					}
				}
				writer.append(line.toString()).append("\n");
			}

			System.out.println("saved to CSV: " + fileName);

		} catch (IOException e) {
			System.err.println(" Failed to write CSV: " + fileName);
			e.printStackTrace();
		}

	}

}
