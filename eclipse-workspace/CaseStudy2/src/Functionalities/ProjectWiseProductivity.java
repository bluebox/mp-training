package Functionalities;

import BaseFiles.EmployeeWorkLog;

import java.util.*;
import java.util.stream.Collectors;

public class ProjectWiseProductivity {
	public static List<String[]> calculate(List<EmployeeWorkLog> logs) {
        Map<String, Map<String, List<Double>>> result = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId,
                Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                    Collectors.mapping(EmployeeWorkLog::getHoursWorked, Collectors.toList()))));

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"Project ID", "Employee ID", "Total Hours", "Average Hours"});
        result.forEach((projectId, empMap) -> {
            empMap.forEach((empId, hoursList) -> {
                double total = hoursList.stream().mapToDouble(Double::doubleValue).sum();
                double avg = total / hoursList.size();
                rows.add(new String[]{projectId, empId, String.format("%.2f", total), String.format("%.2f", avg)});
            });
        });
        return rows;
    }
}