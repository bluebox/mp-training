package employeeCaseStudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class TimePercentagePerCategory {
    public static void solve(List<EmployeeWorkLog> employees) {

    	Map<String, List<EmployeeWorkLog>> groupedByEmployee = employees.stream()
    		    .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId));

    		Map<String, Map<String, Double>> percentageMap = groupedByEmployee.entrySet().stream()
    		    .collect(Collectors.toMap(
    		        e->e.getKey(), 
    		        entry -> {
    		        	List<EmployeeWorkLog> logs = entry.getValue();
    		            double totalHours=logs.stream()
    		            		.mapToDouble(EmployeeWorkLog::getHoursWorked)
    		            		.sum();
    		            if (totalHours==0) return Collections.emptyMap();
    		            Map<String,Double> totalPerCategory = logs.stream()
    		            		.collect(Collectors.groupingBy(
    		            				EmployeeWorkLog::getTaskCategory,
    		            				Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)));
    		            return totalPerCategory.entrySet().stream() 
    		            	    .collect(Collectors.toMap(
    		            	            Map.Entry::getKey,
    		            	            catEntry -> (catEntry.getValue() / totalHours) * 100,
    		            	            (a, b) -> a,
    		            	            LinkedHashMap::new
    		            	        ));

    		        }
    		        
    		    ));

        try (PrintWriter writer = new PrintWriter(new File("results/time_percentage_per_category.csv"))) {
            writer.println("EmployeeId,TaskCategory,Percentage");
            percentageMap.forEach((empId, catMap) ->
                catMap.forEach((category, percentage) ->
                    writer.println(empId + "," + category + "," + String.format("%.2f", percentage))
                )
            );
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to time_percentage_per_category.csv: " + e.getMessage());
        }
    }
}