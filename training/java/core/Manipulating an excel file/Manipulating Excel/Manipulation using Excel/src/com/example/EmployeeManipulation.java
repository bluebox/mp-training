package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class EmployeeManipulation {
	public static void main(String[] args) {
		try {
			FileInputStream f=new FileInputStream("/home/developer/eclipse-workspace/Bhanu/mp-training/training/java/core/Manipulating an excel file/Manipulating Excel/Manipulation using Excel/Input/Sample_Employee_WorkLogs.xlsx");
			Workbook w=WorkbookFactory.create(f);
			Sheet s = w.getSheetAt(0);
			ArrayList<Employee> e=new ArrayList<>();
			for(Row r:s) {
				if(r.getRowNum()==0) {
					continue;
				}
				LocalDate date;
				Cell dateCell = r.getCell(4);
				if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
					date = dateCell.getLocalDateTimeCellValue().toLocalDate();
				} else if (dateCell.getCellType() == CellType.STRING) {
					date = LocalDate.parse(dateCell.getStringCellValue());
				} else {
					date = null;
				}
				Employee e1=new Employee(r.getCell(0).getStringCellValue(), r.getCell(1).getStringCellValue(), r.getCell(2).getStringCellValue(), r.getCell(3).getStringCellValue(), date, r.getCell(5).getStringCellValue(),r.getCell(6).getNumericCellValue(),r.getCell(7).getStringCellValue());
				e.add(e1);
			}
			Thread t1=new Thread(new ExcelThread(e.subList(0, 80), 1));
			t1.start();
			Thread t2=new Thread(new ExcelThread(e.subList(80,160), 2));
			t2.start();
			Thread t3=new Thread(new ExcelThread(e.subList(160, 240), 3));
			t3.start();
			Thread t4=new Thread(new ExcelThread(e.subList(240, 320), 4));
			t4.start();
			Thread t5=new Thread(new ExcelThread(e.subList(320, 400), 5));
			t5.start();
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}

}
