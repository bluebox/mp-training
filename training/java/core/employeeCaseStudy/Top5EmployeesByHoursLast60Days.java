package employeeCaseStudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Top5EmployeesByHoursLast60Days {
	 public static void solve(List<EmployeeWorkLog> employees) {
	     LocalDate sixtyDaysAgo = LocalDate.now().minusDays(60);
	
	     Map<String, Double> totalHours = employees.stream()
	         .filter(e -> e.getDate().isAfter(sixtyDaysAgo))
	         .collect(Collectors.groupingBy(
	             EmployeeWorkLog::getEmployeeId,
	             Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
	         ));
	
	     Map<String, Double> top5 = totalHours.entrySet().stream()
	         .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
	         .limit(5)
	         .collect(Collectors.toMap(
	             Map.Entry::getKey,
	             Map.Entry::getValue,
	             (e1, e2) -> e1,
	             LinkedHashMap::new
	         ));
	
	     try (PrintWriter writer = new PrintWriter(new File("results/top_5_employees_by_hours.csv"))) {
	         writer.println("EmployeeId,TotalHours");
	         top5.forEach((id, hours) -> writer.println(id + "," + String.format("%.2f", hours)));
	     } catch (FileNotFoundException e) {
	         System.err.println("Error writing to top_5_employees_by_hours.csv: " + e.getMessage());
			}
	}
}