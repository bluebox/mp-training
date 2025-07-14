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

public class EmployeesLoggingMoreThan10Hours {

	public static void Q7(List<Employee> employee, List<String> headers) {
//		//Group by employee ID
//		Map<String, List<Employee>> groupedByemployees = employee.stream()
//				.filter(e->e.getHoursWorked()>10.0)
//				.collect(Collectors.groupingBy(e->e.getEmployeeId()));
//		
//		Map<String, List<Employee>> SortedgroupedByemployees = new TreeMap<>(groupedByemployees);
//		
//		for(Map.Entry<String, List<Employee>> m: SortedgroupedByemployees.entrySet()) {
//			System.out.println("{"
//					+ "\n"+m.getKey()+" : " + m.getValue() );				
//		}
		
		
		//Group by Hours worked 
		Map<Double, List<Employee>> groupedByHRW = employee.stream()
				.filter(e->e.getHoursWorked()>10.0)
				.collect(Collectors.groupingBy(e->e.getHoursWorked()));
		
        Map<Double, List<Employee>> SortedgroupedByHRW = new TreeMap<>(groupedByHRW);
        
        for(Map.Entry<Double, List<Employee>> m: SortedgroupedByHRW.entrySet()) {
			System.out.println("{"
					+ "\n"+m.getKey()+" : " + m.getValue() );	
        }
        
     // save to CSV 
        List<String> lines = new ArrayList<>();
        lines.add(String.join(",", headers));

        for (Map.Entry<Double, List<Employee>> m : SortedgroupedByHRW.entrySet()) {
            for (Employee e : m.getValue()) {
                lines.add(employeeToCSV(e));
            }
        }

        Path path = Paths.get("src/output/Q7_EmployeesLoggingMoreThan10Hours.csv");
        
        try {
			Files.createDirectories(path.getParent());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {
			Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        System.out.println("Q7 results exported to Q7_EmployeesLoggingMoreThan10Hours.csv");
		
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
