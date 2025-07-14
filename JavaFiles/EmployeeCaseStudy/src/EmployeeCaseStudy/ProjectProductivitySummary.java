package EmployeeCaseStudy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ProjectProductivitySummary {
	
	public static void Q12(List<Employee> employee) {
		Map<String, Double> totalHoursPerProject = employee.stream()
			    .collect(Collectors.groupingBy(Employee::getProjectId, 
			    		Collectors.summingDouble(Employee::getHoursWorked)));

			Map<String, Double> avgHoursPerProject = employee.stream()
			    .collect(Collectors.groupingBy(Employee::getProjectId, 
			    		Collectors.averagingDouble(Employee::getHoursWorked)));
			
			Map<String, Double> sortedTotalHoursPerProject = new TreeMap<>(totalHoursPerProject);
			
			Map<String, Double> sortedAvgHoursPerProject = new TreeMap<>(avgHoursPerProject);
			
			
			List<String> lines = new ArrayList<>();
		    lines.add("ProjectId,TotalHours,AverageHours");

		    for (String projectId : sortedTotalHoursPerProject.keySet()) {
		        double total = sortedTotalHoursPerProject.get(projectId);
		        double avg = sortedAvgHoursPerProject.getOrDefault(projectId, 0.0);
		        lines.add(projectId + "," + total + "," + avg);
		    }

		    Path path = Paths.get("src/output/Q12_ProjectProductivitySummary.csv");
		    try {
				Files.createDirectories(path.getParent());
			} catch (IOException e) {
				e.printStackTrace();
			}
		    try {
				Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}


			System.out.println("Total Hours Per Project:");
			sortedTotalHoursPerProject.forEach((project, total) -> 
			    System.out.println(project + " : " + total));

			System.out.println("------------------------------------------------------");

			System.out.println("Average Hours Per Project:");
			sortedAvgHoursPerProject.forEach((project, avg) -> 
			    System.out.println(project + " : " + avg));
			
			System.out.println("Q12 summary exported to " + path);
			System.out.println("------------------------------------------------------");

	}


}
