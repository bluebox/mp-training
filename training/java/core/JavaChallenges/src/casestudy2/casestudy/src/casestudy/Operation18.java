package casestudy;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Operation18 {

    public static void execute(List<Employee> employees, String outputFilePath) throws IOException {
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
    }
}



