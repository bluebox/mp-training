package analysis;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import csv.*;
import excel.App;

public class Productivity {
	
	public void AverageHoursPerEmployee()
	{
		App reader = new App();
		List<Employee> employees = reader.readExcel("/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.xlsx");
		Map<String, Double> noOfHrs = employees.stream()
	            .collect(Collectors.groupingBy(Employee::projectId,
	                     Collectors.summingDouble(Employee::hoursWorked)));

	        Map<String, Long> noOfEmp = employees.stream()
	            .collect(Collectors.groupingBy(Employee::projectId,
	                     Collectors.mapping(Employee::employeeId,
	                         Collectors.collectingAndThen(Collectors.toSet(), 
	                         s -> Long.valueOf(s.size())))));
	        List<String[]> csvRows = new ArrayList<>();
	        csvRows.add(new String[] { "Project ID", "Total Hours", "No of Employees", "Average Hours per Employee" });

	        noOfHrs.forEach((id, hours) -> {
	            System.out.println("Project Id :: " + id);
	            System.out.println("No of hours :: " + hours);
	            System.out.println("Average :: " + hours / noOfEmp.get(id));

	            csvRows.add(new String[] {
	                id,
	                String.format("%.2f", hours),
	                String.valueOf(noOfEmp.get(id)),
	                String.format("%.2f", hours / noOfEmp.get(id))
	            });
	        });
	        
	        try (FileWriter writer = new FileWriter("average_hours_per_employee.csv")) {
	            for (String[] row : csvRows) {
	                writer.write(String.join(",", row));
	                writer.write("\n");
	            }
	            System.out.println("\nCSV written successfully to average_hours_per_employee.csv");
	        } catch (IOException e) {
	            System.err.println("Error writing CSV: " + e.getMessage());
	        }
		
	}
	
	public static void main(String[] args) {
		new Productivity().AverageHoursPerEmployee();
	}
	
	
}
