package casestudy;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Operation18 implements Runnable {

 private final List<Employee> employees;
 private final String outputFilePath;

 public Operation18(List<Employee> employees, String outputFilePath) {
     this.employees = employees;
     this.outputFilePath = outputFilePath;
 }

 @Override
 public void run() {
     try {
         Map<String, List<LocalDate>> empDateMap = employees.stream()
                 .filter(e -> e.date != null)
                 .collect(Collectors.groupingBy(Employee::getEmployeeId,
                         Collectors.collectingAndThen(
                                 Collectors.mapping(e -> e.date, Collectors.toSet()),
                                 dates -> dates.stream().sorted().collect(Collectors.toList()))));

         List<String[]> result = new ArrayList<>();

         for (Map.Entry<String, List<LocalDate>> entry : empDateMap.entrySet()) {
             String empId = entry.getKey();
             List<LocalDate> dates = entry.getValue();

             for (int i = 1; i < dates.size(); i++) {
                 if (ChronoUnit.DAYS.between(dates.get(i - 1), dates.get(i)) >= 3) {
                     result.add(new String[]{empId});
                     break;
                 }
             }
         }

         String[] headers = {"Employee ID"};
         EmployeeWriter.writeToExcel(outputFilePath, result, headers);
         System.out.println("Operation 18 completed: " + outputFilePath);
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
}

