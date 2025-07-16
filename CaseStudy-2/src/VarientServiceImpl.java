package com.employee.service;

import com.employee.model.EmployeeWorkLog;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class VarientServiceImpl {

	public static Map<String, List<EmployeeWorkLog>> timeConsuming_PerDepartment(List<EmployeeWorkLog> logs) {
	    // Step 1: Group by department
	    Map<String, List<EmployeeWorkLog>> groupedByDept = logs.stream()
	            .filter(log -> log.getDepartment() != null)
	            .collect(Collectors.groupingBy(EmployeeWorkLog::getDepartment));

	    // Step 2: For each department, sort by Task Category, then Hours Worked (descending), then pick top 3
	    Map<String, List<EmployeeWorkLog>> result = new HashMap<>();
	    for (Map.Entry<String, List<EmployeeWorkLog>> entry : groupedByDept.entrySet()) {
	        List<EmployeeWorkLog> sortedTop3 = entry.getValue().stream()
	                .sorted(Comparator
	                        .comparing(EmployeeWorkLog::getTaskCategory, Comparator.nullsLast(String::compareTo))
	                        .thenComparing(Comparator.comparingDouble(EmployeeWorkLog::getHoursWorked).reversed()))
	                .limit(3)
	                .collect(Collectors.toList());

	        result.put(entry.getKey(), sortedTop3);
	    }

	    return result;
	}



    // Variant 9: Top 5 employees with highest hours in last 60 days
    public static List<Map.Entry<String, Double>> top5EmployeesLast60Days(List<EmployeeWorkLog> logs) {
        LocalDate sixtyDaysAgo = LocalDate.now().minusDays(60);

        return logs.stream()
                .filter(log -> log.getDate() != null && !log.getDate().isBefore(sixtyDaysAgo))
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    // Variant 22: Combine 2 sheets & compute hour differences
    public static Map<String, Double> computeHourDifferences(List<EmployeeWorkLog> sheet1, List<EmployeeWorkLog> sheet2) {
        List<EmployeeWorkLog> combined = new ArrayList<>();
        combined.addAll(sheet1);
        combined.addAll(sheet2);

        return combined.stream()
                .collect(Collectors.groupingBy(
                        log -> log.getEmployeeId() + ":" + log.getDate(),
                        Collectors.mapping(EmployeeWorkLog::getHoursWorked, Collectors.toList())
                ))
                .entrySet().stream()
                .filter(e -> e.getValue().size() == 2)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Math.abs(e.getValue().get(0) - e.getValue().get(1))
                ));
    }

    // Variant 27: Time period-based grouping (morning, afternoon, evening)
    public static Map<String, List<EmployeeWorkLog>> groupByTimePeriod(List<EmployeeWorkLog> logs) {
        return logs.stream()
                .filter(log -> log.getTime() != null)
                .collect(Collectors.groupingBy(log -> EmployeeWorkLog.getTimePeriod(log.getTime())));
    }

    // Variant 30: Analyze total "meeting" time from remarks
    public static double totalMeetingTime(List<EmployeeWorkLog> logs) {
        return logs.stream()
                .filter(log -> log.getRemarks() != null && log.getRemarks().toLowerCase().contains("meeting"))
                .mapToDouble(EmployeeWorkLog::getHoursWorked)
                .sum();
    }
}
