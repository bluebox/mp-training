package casestudy;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Operation10 {

    public static void execute(List<Employee> employees, String outputFilePath) throws IOException {
        Map<String, Map<String, Long>> grouped = employees.stream()
                .filter(e -> "Bug Fix".equalsIgnoreCase(e.taskCategory) && e.date != null)
                .collect(Collectors.groupingBy(e -> e.taskCategory,
                        Collectors.groupingBy(e -> e.date.getDayOfWeek().toString(), Collectors.counting())));

        List<String[]> output = grouped.entrySet().stream()
                .flatMap(entry -> entry.getValue().entrySet().stream()
                        .map(subEntry -> new String[]{entry.getKey(), subEntry.getKey(), String.valueOf(subEntry.getValue())}))
                .collect(Collectors.toList());

        String[] headers = {"Task Category", "Day of Week", "Count"};
        EmployeeWriter.writeToExcel(outputFilePath, output, headers);
        System.out.println("Operation 10 completed: " + outputFilePath);
    }
}






