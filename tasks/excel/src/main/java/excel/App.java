package excel;

import csv.Employee;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class App {

    public ArrayList<Employee> readExcel(String path) {
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

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            boolean isFirstRow = true;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

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

    public static void main(String[] args) {
        App reader = new App();
        //Runnable thread1 = reader::readExcel("/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.xlsx");
        ArrayList<Employee> emps = reader.readExcel("/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.xlsx");
        //ArrayList<Employee>emps = thread1.run();
        emps.forEach(System.out::println);
    }
}
