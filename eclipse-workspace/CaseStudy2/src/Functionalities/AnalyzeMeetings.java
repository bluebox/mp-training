package Functionalities;

import BaseFiles.EmployeeWorkLog;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyzeMeetings {
	public static List<String[]> calculate(List<EmployeeWorkLog> logs) {
        Map<String, Double> empMeetingHours = logs.stream()
            .filter(log -> log.getRemarks().toLowerCase().contains("meeting"))
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)));

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"Employee ID", "Total Meeting Hours"});

        empMeetingHours.forEach((empId, totalHours) -> {
            rows.add(new String[]{empId, String.format("%.2f", totalHours)});
        });

        return rows;
    }
}