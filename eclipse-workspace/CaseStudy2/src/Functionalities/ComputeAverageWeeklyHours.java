package Functionalities;

import BaseFiles.EmployeeWorkLog;

import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class ComputeAverageWeeklyHours {
	public static List<String[]> calculate(List<EmployeeWorkLog> logs) {
        Map<String, Map<YearMonth, Double>> result = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                Collectors.groupingBy(log -> YearMonth.from(log.getDate()),
                    Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))));

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"Employee ID", "Year-Month", "Average Weekly Hours"});
        result.forEach((empId, monthMap) -> {
            monthMap.forEach((yearMonth, totalHours) -> {
                double avgWeekly = totalHours / 5.0;
                rows.add(new String[]{empId, yearMonth.toString(), String.format("%.2f", avgWeekly)});
            });
        });
        return rows;
    }
}