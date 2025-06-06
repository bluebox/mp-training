package com.employee;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;

import com.employee.domain.Employee;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hey");
		String filePath = "data.xlsx";
        ArrayList<Employee> employees = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

	}

}
