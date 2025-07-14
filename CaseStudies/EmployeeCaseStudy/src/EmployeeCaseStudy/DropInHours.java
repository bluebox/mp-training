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
import java.time.Month;

public class DropInHours {
	public static void Q20(List<Employee> employee) {
		
		Map<String, TreeMap<Month, Double>> employeeMonthHours = employee.stream()
			    .collect(Collectors.groupingBy(Employee::getEmployeeId,
			        Collectors.groupingBy(e -> e.getDate().getMonth(), TreeMap::new,
			            Collectors.summingDouble(Employee::getHoursWorked)
			        )
			    ));
		
		Map<String, TreeMap<Month, Double>> SortedemployeeMonthHours  = new TreeMap<>(employeeMonthHours);

		
		for (Map.Entry<String, TreeMap<Month, Double>> entry : SortedemployeeMonthHours.entrySet()) {
		    String empId = entry.getKey();
		    Map<Month, Double> monthHours = entry.getValue();
		    System.out.println(empId+" : "+monthHours);

		}

		List<String> lines = new ArrayList<>();
	    lines.add("EmployeeId,FromMonth,ToMonth,DropPercent");
		
		for (Map.Entry<String, TreeMap<Month, Double>> entry : SortedemployeeMonthHours.entrySet()) {
		    String empId = entry.getKey();
		    Map<Month, Double> monthHours = entry.getValue();

		    List<Month> Months = monthHours.keySet().stream()
		    		.collect(Collectors.toList());
		    
		    for (int i = 1; i < Months.size(); i++) {
		        double prev = monthHours.get(Months.get(i - 1));
		        double curr = monthHours.get(Months.get(i));
		        
		        if (prev > 0 && ((prev - curr) / prev) > 0.4) {
		            System.out.println(empId + " dropped hours by more than 40% from " +
		                Months.get(i - 1) + " to " + Months.get(i));
		            double dropPercent = ((prev - curr) / prev) * 100;
	                lines.add(empId + "," + Months.get(i - 1) + "," + Months.get(i) + "," + dropPercent);
	            
		        }
		    }
		}
		
		Path path = Paths.get("src/output/Q20_DropInHours.csv");
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

	    System.out.println("Q20 results exported to " + path);
		
		
		System.out.println("------------------------------------------------------");

	}

}
