package employeeCaseStudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryConsistencyAnalysis {
    public static void solve(List<EmployeeWorkLog> employees) {
        Map<String, List<EmployeeWorkLog>> groupedByCategory = employees.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getTaskCategory));

        Map<String, Double> categoryVariance = groupedByCategory.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> {
                    double avg = entry.getValue().stream()
                        .mapToDouble(EmployeeWorkLog::getHoursWorked)
                        .average()
                        .orElse(0.0);

                    return entry.getValue().stream()
                        .mapToDouble(e -> Math.pow(e.getHoursWorked() - avg, 2))
                        .average()
                        .orElse(0.0);
                }
            ));

        Map<String, Double> sortedByVariance = categoryVariance.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));

        try (PrintWriter writer = new PrintWriter(new File("results/category_consistency_analysis.csv"))) {
            writer.println("TaskCategory,Variance");
            sortedByVariance.forEach((category, variance) ->
                writer.println(category + "," + String.format("%.4f", variance))
            );
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to category_consistency_analysis.csv: " + e.getMessage());
        }
    }
}