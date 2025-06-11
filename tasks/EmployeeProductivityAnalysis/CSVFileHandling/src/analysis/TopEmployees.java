package analysis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import csv.CsvReader;
import csv.Employee;
import excel.ExcelReader;

public class TopEmployees {
	
	public void computeTop()
	{
		CsvReader reader = new CsvReader();
		List<Employee> employees = ExcelReader.readExcel();//reader.decipherCSV(reader.readCSV("/root/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.csv"));
		
		Map<String,List<Employee>> topEmp= employees.stream().collect(Collectors.groupingBy(Employee::department,Collectors.collectingAndThen(Collectors.toList(), list->list.stream().sorted(Comparator.comparingDouble(Employee::hoursWorked).reversed()).limit(2).collect(Collectors.toList()))));
		topEmp.forEach((dept, list) -> {
		    System.out.println("Department: " + dept);
		    list.forEach(emp -> System.out.println("  " + emp.name() + " - " + emp.hoursWorked()));
		});
		
		
		List<String[]> csvRows = new ArrayList<>();
        csvRows.add(new String[]{"Department", "Employee ID", "Name", "Project ID", "Date", "Task Category", "Hours Worked", "Remarks"});

        topEmp.forEach((dept, empList) -> {
            for (Employee emp : empList) {
                csvRows.add(new String[]{
                    dept,
                    emp.employeeId(),
                    emp.name(),
                    emp.projectId(),
                    emp.date().toString(),
                    emp.taskCatagory(),
                    String.valueOf(emp.hoursWorked()),
                    emp.remarks()
                });
            }
        });
        
        try (FileWriter writer = new FileWriter("top_employees_per_department.csv")) {
            for (String[] row : csvRows) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
            System.out.println("\nCSV export successful to top_employees_per_department.csv");
        } catch (IOException e) {
            System.err.println("CSV write error: " + e.getMessage());
        }
		
	}
	public static void main(String[] args) {
		new TopEmployees().computeTop();
	}

}
