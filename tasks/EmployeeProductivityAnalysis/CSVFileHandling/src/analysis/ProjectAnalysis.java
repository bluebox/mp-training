package analysis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import csv.CsvReader;
import csv.Employee;

public class ProjectAnalysis {
	
	public void projectHours()
	{
		CsvReader reader = new CsvReader();
		List<Employee> employees = reader.decipherCSV(reader.readCSV("/root/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.csv"));
		
		Map<String, Map<String, Double>> deptProjectHours = employees.stream().collect(Collectors.groupingBy(Employee::department,Collectors.groupingBy(Employee::projectId,Collectors.summingDouble(Employee::hoursWorked))));
		
		Map<String, List<Map.Entry<String, Double>>> deptSortedProj = deptProjectHours.entrySet().stream()
			    .collect(Collectors.toMap(
			        Map.Entry::getKey, // department
			        e -> e.getValue().entrySet().stream()
			              .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
			              .collect(Collectors.toList())
			    ));

		deptSortedProj.forEach((dept, projectList) -> {
            System.out.println("Department: " + dept);
            projectList.forEach(ph ->
                System.out.printf("  Project: %s %.2f hrs%n", ph.getKey(), ph.getValue()));
        });
		
		
		List<String[]> csvRows = new ArrayList<>();
        csvRows.add(new String[]{"Department", "Project ID", "Total Hours"});

        deptSortedProj.forEach((dept, projectList) -> {
            for (Map.Entry<String, Double> proj : projectList) {
                csvRows.add(new String[]{
                    dept,
                    proj.getKey(),
                    String.format("%.2f", proj.getValue())
                });
            }
        });
        
        try (FileWriter writer = new FileWriter("department_project_hours.csv")) {
            for (String[] row : csvRows) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
            System.out.println("\nCSV export successful to department_project_hours.csv");
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
		
	}
	public static void main(String[] args) {
		new ProjectAnalysis().projectHours();
	}

}
