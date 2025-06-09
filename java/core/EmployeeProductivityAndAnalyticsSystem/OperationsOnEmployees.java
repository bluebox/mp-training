package EmployeeProductivityAndAnalyticsSystem;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OperationsOnEmployees {
	

	public Map<String, Map<String, Double>> employeeAverageWeeklyHours(List<EmployeeWorkLog> employees) {
	    System.out.println("\t\tGroup by employee and month, average of weekly hours.");
	    System.out.println("--------------------------------------------------------------------------");

	    Map<String, Map<String, Double>> resultData = employees.stream()
	        .collect(Collectors.groupingBy(
	            EmployeeWorkLog::getEmployeeId,
	            Collectors.groupingBy(
	                e -> e.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM")),
	                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
	            )
	        ));

	    // for console 
	    resultData.forEach((empId, monthMap) -> {
	        monthMap.forEach((month, totalHours) -> {
	            double avgWeekly = totalHours / 4.0;
	            System.out.printf("%s - %s : Avg Weekly Hours = %.2f%n", empId, month, avgWeekly);
	        });
	    });

	    return resultData;
	}

	
	public List<EmployeeWorkLog> heighestHoursWorkedEmps(List<EmployeeWorkLog> employees) {
      System.out.println("\t\tTop 5 employees with highest hours in last 60 days");
      System.out.println("--------------------------------------------------------------------------");
      
      LocalDate sixtyDaysAgo = LocalDate.now().minusDays(60);
      
	    List<EmployeeWorkLog> filteredEmployees = employees.stream()
	        .filter(e -> !e.getDate().isBefore(sixtyDaysAgo))
	        .sorted(Comparator.comparingDouble(EmployeeWorkLog::getHoursWorked).reversed())
	        .limit(5)
	        .collect(Collectors.toList());
	    
//	    for console 
	    filteredEmployees.stream()
	    	.forEach(System.out::println);
	    
	    return filteredEmployees;
	}


	
	public List<List<String>> criticalProjects(List<EmployeeWorkLog> employees) {
	    System.out.println("\t\tCritical projects: greater than 2 employees and 20+ hours");
	    System.out.println("--------------------------------------------------------------------------");

	    Map<String, Set<String>> projectToEmployees = employees.stream()
	        .collect(Collectors.groupingBy(
	            EmployeeWorkLog::getProjectId,
	            Collectors.mapping(EmployeeWorkLog::getEmployeeId, Collectors.toSet())
	        ));

	    Map<String, Double> projectToHours = employees.stream()
	        .collect(Collectors.groupingBy(
	            EmployeeWorkLog::getProjectId,
	            Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
	        ));

	    List<List<String>> resultData = projectToEmployees.entrySet().stream()
	        .filter(e -> e.getValue().size() > 2 && projectToHours.getOrDefault(e.getKey(), 0.0) >= 20.0)
	        .map(e -> {
	            String project = e.getKey();
	            int empCount = e.getValue().size();
	            double totalHours = projectToHours.get(project);
	            System.out.printf("Project : %s,  Employees :  %s,  Total Hours : %.2f%n", project, empCount, totalHours);
	            return Arrays.asList(project, String.valueOf(empCount), String.format("%.2f", totalHours));
	        })
	        .collect(Collectors.toList());

	    if (resultData.isEmpty()) {
	    	resultData.add(Arrays.asList("There is none of the project available in given criteria", "", ""));
	        System.out.println("There is none of the project available in given category");
	    }

	    return resultData;
	}

	
	public List<String> sortByDeptProjectDate(List<EmployeeWorkLog> employees) {
		
    	System.out.println("		sort by department->ProjectId->Date ");
		System.out.println("--------------------------------------------------------------------------");
		
	    employees.sort(Comparator
	        .comparing(EmployeeWorkLog::getDepartment)
	        .thenComparing(EmployeeWorkLog::getProjectId)
	        .thenComparing(EmployeeWorkLog::getDate));

	    List<String> resultData = new ArrayList<>();
	    for (EmployeeWorkLog emp : employees) {
	    	resultData.add(emp.toString());
	    }
	    
//	    for console
	    resultData.stream()
	    .forEach(System.out::println);
	    
	    return resultData;
	}

}
