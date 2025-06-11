
package casestudy;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Operation3 implements Runnable {

 private final List<Employee> employees;
 private final String outputFilePath;

 public Operation3(List<Employee> employees, String outputFilePath) {
     this.employees = employees;
     this.outputFilePath = outputFilePath;
 }

 @Override
 public void run() {
     try {
         Map<String, Double> projectHours = employees.stream()
                 .filter(e -> e.date != null && e.date.getMonthValue() >= 3 && e.date.getMonthValue() <= 5)
                 .collect(Collectors.groupingBy(e -> e.projectId,
                         Collectors.summingDouble(e -> e.hoursWorked)));

         List<String[]> output = projectHours.entrySet().stream()
                 .filter(entry -> entry.getValue() > 100)
                 .map(entry -> new String[]{entry.getKey(), String.format("%.2f", entry.getValue())})
                 .collect(Collectors.toList());

         String[] headers = {"Project ID", "Total Hours Worked"};
         EmployeeWriter.writeToExcel(outputFilePath, output, headers);
         System.out.println("Operation 3 completed: " + outputFilePath);
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
}

