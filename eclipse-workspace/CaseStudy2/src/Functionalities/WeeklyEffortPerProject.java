package Functionalities;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import BaseFiles.EmployeeWorkLog;

public class WeeklyEffortPerProject {
	public static List<String[]> calculate(List<EmployeeWorkLog> logs) {
        LocalDate cutoffDate = LocalDate.now().minusDays(60);

        Map<String, Double> empHours = logs.stream()
            .filter(log -> log.getDate().isAfter(cutoffDate))
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                    Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)));

        List<Map.Entry<String, Double>> top5 = empHours.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(5)
            .collect(Collectors.toList());

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"Employee ID", "Total Hours (Last 60 Days)"});
        for (Map.Entry<String, Double> entry : top5) {
            rows.add(new String[]{entry.getKey(), String.format("%.2f", entry.getValue())});
        }
        return rows;
	}
}