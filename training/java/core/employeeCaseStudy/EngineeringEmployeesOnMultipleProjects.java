package employeeCaseStudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class EngineeringEmployeesOnMultipleProjects {
	 public static void solve(List<EmployeeWorkLog> employees) {
	     Map<String, Set<String>> projectMap = employees.stream()
	         .filter(e -> "Engineering".equalsIgnoreCase(e.getDepartment()))
	         .collect(Collectors.groupingBy(
	             EmployeeWorkLog::getEmployeeId,
	             Collectors.mapping(EmployeeWorkLog::getProjectId, Collectors.toSet())
	         ));
	
	     Map<String, Integer> filteredResult = projectMap.entrySet().stream()
	         .filter(e -> e.getValue().size() > 2)
	         .sorted(Comparator.comparingInt(e -> e.getValue().size()))
	         .collect(Collectors.toMap(
	             Map.Entry::getKey,
	             e -> e.getValue().size(),
	             (e1, e2) -> e1,
	             LinkedHashMap::new
	         ));
	
	     try (PrintWriter writer = new PrintWriter(new File("results/engineering_employees_on_multiple_projects.csv"))) {
	         writer.println("EmployeeId,UniqueProjectCount");
	         filteredResult.forEach((id, count) -> writer.println(id + "," + count));
	     } catch (FileNotFoundException e) {
	         System.err.println("Error writing to engineering_employees_on_multiple_projects.csv: " + e.getMessage());
	     }
	 }
}