package EmployeeCaseStudy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DropInHours {
	public static void Q20(List<Employee> employee) {
		
		Map<String, Map<String, Double>> employeeMonthHours = employee.stream()
			    .collect(Collectors.groupingBy(Employee::getEmployeeId,
			        Collectors.groupingBy(e -> e.getDate().getMonth()
			        		.toString() + "-" + e.getDate().getYear(),
			            Collectors.summingDouble(Employee::getHoursWorked)
			        )
			    ));

		List<String> lines = new ArrayList<>();
	    lines.add("EmployeeId,FromMonth,ToMonth,DropPercent");
		
			for (Map.Entry<String, Map<String, Double>> entry : employeeMonthHours.entrySet()) {
			    String empId = entry.getKey();
			    Map<String, Double> monthHours = entry.getValue();

			    // Sort months chronologically
			    List<String> sortedMonths = monthHours.keySet().stream().
			    		sorted().collect(Collectors.toList());
			    for (int i = 1; i < sortedMonths.size(); i++) {
			        double prev = monthHours.get(sortedMonths.get(i - 1));
			        double curr = monthHours.get(sortedMonths.get(i));
			        if (prev > 0 && ((prev - curr) / prev) > 0.4) {
			            System.out.println(empId + " dropped hours by more than 40% from " +
			                sortedMonths.get(i - 1) + " to " + sortedMonths.get(i));
			            double dropPercent = ((prev - curr) / prev) * 100;
		                lines.add(empId + "," + sortedMonths.get(i - 1) + "," + sortedMonths.get(i) + "," + dropPercent);
		            
			        }
			    }
			}
			
			Path path = Paths.get("src/output/Q20_DropInHours.csv");
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

		    System.out.println("Q20 results exported to " + path);
			
			
			System.out.println("------------------------------------------------------");

	}

}
