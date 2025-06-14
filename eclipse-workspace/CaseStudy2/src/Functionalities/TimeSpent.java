package Functionalities;

import BaseFiles.EmployeeWorkLog;

import java.util.*;
import java.util.stream.Collectors;

public class TimeSpent {
	public static List<String[]> calculate(List<EmployeeWorkLog> logs) {
        Map<String, Map<String, Double>> empCategoryHours = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                Collectors.groupingBy(EmployeeWorkLog::getTaskCategory,
                    Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))));

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"Employee ID", "Task Category", "Percentage of Time"});

        empCategoryHours.forEach((empId, categoryMap) -> {
            double total = categoryMap.values().stream().mapToDouble(Double::doubleValue).sum();
            categoryMap.forEach((cat, hours) -> {
                double percent = (hours / total) * 100.0;
                rows.add(new String[]{empId, cat, String.format("%.2f", percent)});
            });
        });

        return rows;
    }
}