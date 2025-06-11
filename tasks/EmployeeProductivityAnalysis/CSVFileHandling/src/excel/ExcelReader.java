package excel;

import csv.Employee;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

    public ArrayList<Employee> readExcel(String path, int startRow,int endRow) {
        ArrayList<Employee> employees = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path)) {
            Workbook workbook;

            if (path.toLowerCase().endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (path.toLowerCase().endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("Unsupported file type: " + path);
            }
//            workbook = new HSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            boolean isFirstRow = true;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if(row.getRowNum() <startRow || row.getRowNum()>endRow)
                {
                	continue;
                }
            
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

               
                String employeeId = "", name = "", department = "", projectId = "", taskCatagory = "", remarks = "";
                LocalDate date = null;
                double hoursWorked = 0.0;

               
                try {
                    Cell cell;

                    cell = row.getCell(0);
                    if (cell != null) employeeId = cell.getStringCellValue();

                    cell = row.getCell(1);
                    if (cell != null) name = cell.getStringCellValue();

                    cell = row.getCell(2);
                    if (cell != null) department = cell.getStringCellValue();

                    cell = row.getCell(3);
                    if (cell != null) projectId = cell.getStringCellValue();

                    cell = row.getCell(4);
                    if (cell != null) {
                        String locDate = cell.getStringCellValue();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        date = LocalDate.parse(locDate, formatter);
                    }

                    cell = row.getCell(5);
                    if (cell != null) taskCatagory = cell.getStringCellValue();

                    cell = row.getCell(6);
                    if (cell != null) hoursWorked = cell.getNumericCellValue();

                    cell = row.getCell(7);
                    if (cell != null) remarks = cell.getStringCellValue();

                    
                    employees.add(new Employee(employeeId, name, department, projectId, date, taskCatagory, hoursWorked, remarks));
                } catch (Exception e) {
                    System.err.println("Error parsing row " + row.getRowNum() + ": " + e.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
    
    public static List<Employee>readExcel()
    {
    	ExcelReader reader = new ExcelReader();
        List<Employee>emps = Collections.synchronizedList(new ArrayList<Employee>());
        Runnable thread1 = ()->{
        	List<Employee>empPartial = reader.readExcel("/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.xlsx", 0, 166);
        	empPartial.forEach(e->emps.add(e));
        };
        Runnable thread2 = ()->{
        	List<Employee>empPartial = reader.readExcel("/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.xlsx", 167, 223);
        	empPartial.forEach(e->emps.add(e));
        };
        Runnable thread3 = ()->{
        	List<Employee>empPartial = reader.readExcel("/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.xlsx", 224, 400);
        	empPartial.forEach(e->emps.add(e));
        };
        //ArrayList<Employee> emps = reader.readExcel("/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.xlsx");
        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);
        Thread t3 = new Thread(thread3);
        t1.start();
        t2.start();
        t3.start();
        try {
        	t1.join();
        	t2.join();
        	t3.join();
        }catch (InterruptedException e) {
        	e.printStackTrace();
		}
        emps.forEach(System.out::println);
        return emps;
    }

    public static void main(String[] args) {
        
    }
}
