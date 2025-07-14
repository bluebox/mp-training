package EmployeeCaseStudy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.time.Month;

public class EmployeesChangingProjects {
	public static void Q33(List<Employee> employee,List<String> headers) {
		
		Map<String, TreeMap<Month, List<String>>> empMonthProjects = employee.stream()
			    .collect(Collectors.groupingBy(Employee::getEmployeeId,
			        Collectors.groupingBy(e -> e.getDate()
			        		.getMonth(), TreeMap::new ,
			            Collectors.mapping(Employee::getProjectId, Collectors.toList())
			        )
			    ));
		
		Map<String, TreeMap<Month, List<String>>> SortedempMonthProjects = new TreeMap<>(empMonthProjects);
		
		for (Map.Entry<String, TreeMap<Month, List<String>>> entry : SortedempMonthProjects.entrySet()) {
		    String empId = entry.getKey();
		    TreeMap<Month, List<String>> monthProjects = entry.getValue();
		    System.out.println(empId+" : "+monthProjects);

		}
		
		List<String> lines = new ArrayList<>();
	    lines.add(String.join(",", headers));

		for (Map.Entry<String, TreeMap<Month, List<String>>> entry : SortedempMonthProjects.entrySet()) {
		    String empId = entry.getKey();
		    TreeMap<Month, List<String>> monthProjects = entry.getValue();
		    for (Map.Entry<Month, List<String>> monthEntry : monthProjects.entrySet()) {
		        long uniqueProjects = monthEntry.getValue().stream().distinct().count();
		        if (uniqueProjects > 1) {
		            System.out.println(empId + " changed projects more than once in " + monthEntry.getKey());
		            
		            employee.stream()
                    .filter(e -> e.getEmployeeId().equals(empId) &&
                                 (e.getDate().getMonth().toString())
                                 .equals(monthEntry.getKey().toString()))
                    .forEach(e -> lines.add(employeeToCSV(e)));
		        }
		    }
		}
			
		Path path = Paths.get("src/output/Q33_EmployeesChangingProjects.csv");
	    try {
			Files.createDirectories(path.getParent());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    try {
			Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	    System.out.println("Q33 results exported to " + path);
		System.out.println("------------------------------------------------------");

		
	}
	
	
	private static String employeeToCSV(Employee e) {
	    return String.join(",", Arrays.asList(
	    		  e.getEmployeeId(),
                  e.getName(),
                  e.getDepartment(),
                  e.getProjectId(),
                  String.valueOf(e.getDate()),
                  e.getTaskCategory(),
                  String.valueOf(e.getHoursWorked()),
                  e.getRemarks()
	    ));
	}


}
