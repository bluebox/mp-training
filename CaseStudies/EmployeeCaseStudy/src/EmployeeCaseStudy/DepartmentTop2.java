package EmployeeCaseStudy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DepartmentTop2 {
	
	public static void Q24(List<Employee> employee, List<String> headers) {
		
		Map<String, List<Employee>> DepTop2 = employee.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						
						Collectors.collectingAndThen(Collectors.toList(), list-> list.stream()
								.sorted(Comparator.comparingDouble(Employee::getHoursWorked).reversed())
								.limit(2)
								.collect(Collectors.toList())
								)
						
		));
		
		Map<String, List<Employee>> SortedDepTop2 = new TreeMap<>(DepTop2);
		
		 List<String> lines = new ArrayList<>();
		  lines.add(String.join(",", headers));

		    for (Map.Entry<String, List<Employee>> m : SortedDepTop2.entrySet()) {
		    	System.out.println("{"
						+ "\n"+m.getKey()+" : " + m.getValue() );	
		        for (Employee e : m.getValue()) {
		            lines.add(employeeToCSV(e));
		        }
		    }

		    Path path = Paths.get("src/output/Q24_DepartmentTop2.csv");
		    try {
				Files.createDirectories(path.getParent());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    System.out.println("Q24 results exported to " + path);
		
		
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
