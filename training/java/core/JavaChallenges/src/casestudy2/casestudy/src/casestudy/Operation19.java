package casestudy;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Operation19 {

    public static void execute(List<Employee> employees, String outputFilePath) throws IOException {
        Map<String, List<Double>> categoryHours = employees.stream()
                .collect(Collectors.groupingBy(e -> e.taskCategory,
                        Collectors.mapping(e -> e.hoursWorked, Collectors.toList())));

        Map<String, Double> categoryVariance = new LinkedHashMap<>();

        for (Map.Entry<String, List<Double>> entry : categoryHours.entrySet()) {
            List<Double> hoursList = entry.getValue();
            double avg = hoursList.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            double variance = hoursList.stream()
                    .mapToDouble(h -> (h - avg) * (h - avg))
                    .average()
                    .orElse(0.0);
            categoryVariance.put(entry.getKey(), variance);
        }

        LinkedHashMap<String, Double> sorted = categoryVariance.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));

        List<String[]> result = sorted.entrySet().stream()
                .map(e -> new String[]{e.getKey(), String.format("%.4f", e.getValue())})
                .collect(Collectors.toList());

        String[] headers = {"Task Category", "Variance in Hours Worked"};
        EmployeeWriter.writeToExcel(outputFilePath, result, headers);
        System.out.println("Operation 19 completed: " + outputFilePath);
    }
}



