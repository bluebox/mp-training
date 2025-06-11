package casestudy;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Operation3 {

    public static void execute(List<Employee> employees, String outputFilePath) throws IOException {
        Map<String, Double> projectHours = employees.stream()
                .filter(e -> e.date != null && e.date.getMonthValue() >= 3 && e.date.getMonthValue() <= 5)
                .collect(Collectors.groupingBy(e -> e.projectId,
                        Collectors.summingDouble(e -> e.hoursWorked)));

        List<String[]> output = projectHours.entrySet().stream()
                .filter(entry -> entry.getValue() > 20)
                .map(entry -> new String[]{entry.getKey(), String.format("%.2f", entry.getValue())})
                .collect(Collectors.toList());

        String[] headers = {"Project ID", "Total Hours Worked"};
        EmployeeWriter.writeToExcel(outputFilePath, output, headers);
        System.out.println("Operation 3 completed: " + outputFilePath);
    }
}



