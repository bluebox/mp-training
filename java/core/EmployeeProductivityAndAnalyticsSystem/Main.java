package EmployeeProductivityAndAnalyticsSystem;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class Main {

    public static void main(String[] args) {
        ArrayList<EmployeeWorkLog> employees = new ArrayList<>();

        for (int i = 1; i <= 400; i++) {
            String empid = ReadExcel("Sheet1", i, 0);
            String name = ReadExcel("Sheet1", i, 1);
            String dept = ReadExcel("Sheet1", i, 2);
            String projectId = ReadExcel("Sheet1", i, 3);
            String dateStr = ReadExcel("Sheet1", i, 4);
            String task = ReadExcel("Sheet1", i, 5);
            String hoursWorkedStr = ReadExcel("Sheet1", i, 6);
            String remarks = ReadExcel("Sheet1", i, 7);

            LocalDate date = dateStr.isEmpty() ? null : LocalDate.parse(dateStr);
            double hoursWorked = hoursWorkedStr.isEmpty() ? 0.0 : Double.parseDouble(hoursWorkedStr);
            EmployeeWorkLog emp = new EmployeeWorkLog(empid, name, dept, projectId, date, task, hoursWorked, remarks);
            employees.add(emp);
        }
        
        //All employee records
//        employees.stream()
//    	.forEach(System.out::println);

        OperationsOnEmployees opeOnEmp = new OperationsOnEmployees();

//        calling operation methods 
//        Weekly Average hours employees month wise
        Thread t1 = new Thread(()->{
        	
        	Map<String, Map<String, Double>> empAvgHours = opeOnEmp.employeeAverageWeeklyHours(employees);
        	
        	int sheet2row = 0;
        	WriteExcel("Sheet2", sheet2row, 0, "Emp ID");
        	WriteExcel("Sheet2", sheet2row, 1, "Month");
        	WriteExcel("Sheet2", sheet2row, 2, "Avg Weekly Hours");
        	sheet2row++;
        	
        	for (String empId : empAvgHours.keySet()) {
        		for (String month : empAvgHours.get(empId).keySet()) {
        			double avg = empAvgHours.get(empId).get(month) / 4.0;
        			WriteExcel("Sheet2", sheet2row, 0, empId);
        			WriteExcel("Sheet2", sheet2row, 1, month);
        			WriteExcel("Sheet2", sheet2row, 2, String.format("%.2f", avg));
        			sheet2row++;
        		}
        	}
        	
        });

//        Top 5 emolyees with heighest hours in last 60 days
        Thread t2 = new Thread(()->{
        	
        	List<EmployeeWorkLog> empList = opeOnEmp.heighestHoursWorkedEmps(employees);
        	
        	int sheet3row = 0;
        	WriteExcel("Sheet3", sheet3row++, 0, "Top 5 Employees (in the Last 60 Days)");
        	WriteExcel("Sheet3", sheet3row, 0, "Emp ID");
        	WriteExcel("Sheet3", sheet3row, 1, "Name");
        	WriteExcel("Sheet3", sheet3row, 2, "Hours");
        	sheet3row++;
        	
        	for (EmployeeWorkLog emp : empList) {
        		WriteExcel("Sheet3", sheet3row, 0, emp.getEmployeeId());
        		WriteExcel("Sheet3", sheet3row, 1, emp.getName());
        		WriteExcel("Sheet3", sheet3row, 2, String.valueOf(emp.getHoursWorked()));
        		sheet3row++;
        	}
        	
        });
        
//        Critical projects : > 2 employees and morethan 20 + hours
        Thread t3 = new Thread(()->{
        	
        	List<List<String>> criticalProjects = opeOnEmp.criticalProjects(employees);
        	
        	int sheet4row = 0;
        	WriteExcel("Sheet4", sheet4row++, 0, "Critical Projects (More than 2 Employees & 20+ Total Hours)");
        	WriteExcel("Sheet4", sheet4row, 0, "Project");
        	WriteExcel("Sheet4", sheet4row, 1, "Employees");
        	WriteExcel("Sheet4", sheet4row++, 2, "Total Hours");
        	
        	for (List<String> proj : criticalProjects) {
        		int col = 0;
        		for (String val : proj) {
        			WriteExcel("Sheet4", sheet4row, col++, val);
        		}
        		sheet4row++;
        	}
        });
        
//        Sorted Employess Dept -> Project -> Date
        
        Thread t4 = new Thread(()->{
        	
        	List<String> sortedEmployeeLogs = opeOnEmp.sortByDeptProjectDate(employees);
        	
        	int sheet5row = 0;
        	WriteExcel("Sheet5", sheet5row, 0, "Sorted Employee Logs by Dept → Project → Date");
        	sheet5row++;
        	
        	WriteExcel("Sheet5", sheet5row, 0, "Emp ID");
        	WriteExcel("Sheet5", sheet5row, 1, "Name");
        	WriteExcel("Sheet5", sheet5row, 2, "Department");
        	WriteExcel("Sheet5", sheet5row, 3, "Project ID");
        	WriteExcel("Sheet5", sheet5row, 4, "Date");
        	WriteExcel("Sheet5", sheet5row, 5, "Task Category");
        	WriteExcel("Sheet5", sheet5row, 6, "Hours Worked");
        	WriteExcel("Sheet5", sheet5row, 7, "Remarks");
        	sheet5row++;
        	
        	for (String line : sortedEmployeeLogs) {
        		
        		String[] parts = line.trim().split("\\s{2,}");
        		for (int column = 0; column < parts.length && column < 8; column++) {
        			WriteExcel("Sheet5", sheet5row, column, parts[column]);
        		}
        		sheet5row++;
        	}
        });
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        try {
        	t1.join();
        	t2.join();
        	t3.join();
        	t4.join();
        }
        catch(InterruptedException e) {
        	e.printStackTrace();
        }


    }
    

    public static String ReadExcel(String SheetName, int rNum, int cNum) {
        String data = "";

        try (FileInputStream fis = new FileInputStream("/home/developer/eclipse-workspace/MP_Training/src/EmployeeProductivityAndAnalyticsSystem/Sample_Employee_WorkLogs1.xlsx")) {
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet(SheetName);
            if (s == null) 
            	return "";
            Row r = s.getRow(rNum);
            if (r == null) 
            	return "";
            Cell c = r.getCell(cNum);
            if (c == null) 
            	return "";

            switch (c.getCellType()) {
                case STRING->
                    data = c.getStringCellValue();
                case NUMERIC->{
	                    if (DateUtil.isCellDateFormatted(c)) {
	                        data = c.getLocalDateTimeCellValue().toLocalDate().toString();
	                    } else {
	                        data = String.valueOf(c.getNumericCellValue());
	                        if (data.endsWith(".0")) {
	                            data = data.substring(0, data.length() - 2);
	                        }
	                    }
                	}
                case BLANK->
                    data = "";
                default->
                    data = "Unsupported cell type";
            }

        } catch (Exception e) {
            System.out.println("Excel read exception");
            e.printStackTrace();
        }

        return data;
    }
    
    
    public static synchronized void WriteExcel(String SheetName, int rNum, int cNum,String data) {
        try {
            FileInputStream fis = new FileInputStream("/home/developer/eclipse-workspace/MP_Training/src/EmployeeProductivityAndAnalyticsSystem/Sample_Employee_WorkLogs1.xlsx");
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet(SheetName);
            if (s == null) {
                s = wb.createSheet(SheetName);
            }

            Row r = s.getRow(rNum);
            if (r == null) {
                r = s.createRow(rNum);
            }

            Cell c = r.getCell(cNum);
            if (c == null) {
                c = r.createCell(cNum);
            }

            c.setCellValue(data);

            FileOutputStream fos = new FileOutputStream("/home/developer/eclipse-workspace/MP_Training/src/EmployeeProductivityAndAnalyticsSystem/Sample_Employee_WorkLogs1.xlsx");
            wb.write(fos);

        } catch (Exception e) {
            System.out.println("Excel write exception");
            e.printStackTrace();
        }
    }    
}
