package com.demo.employeeelog;
import com.opencsv.CSVWriter;
import java.util.*;

import java.util.stream.*;
import java.time.DayOfWeek;
import java.util.Map;


public class tasks {

    /**
     * Task 1: Logs from Mar–May; group by project; total hours > 100.
     */
	public static void task1_MarToMayOver100(List<EmployeeWorkLog> logs) throws Exception {
	    Map<String, Double> result = logs.stream()
	        .filter(log -> log.getDate() != null &&
	                       log.getDate().getMonthValue() >= 3 &&
	                       log.getDate().getMonthValue() <= 5) // March–May
	        .collect(Collectors.groupingBy(
	            EmployeeWorkLog::getProjectId,
	            Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
	        ))
	        .entrySet().stream()
	        .filter(entry -> entry.getValue() > 100)
	        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	    List<String[]> rows = new ArrayList<>();
	    rows.add(new String[]{"Project ID", "Total Hours"});
	    result.forEach((projectId, totalHours) ->
	        rows.add(new String[]{projectId, String.valueOf(totalHours)}));
	    
	    CsvExporter.writeToCsv(rows, "task1_mar_to_may.csv");
	    System.out.println(result);
	}

    /**
     * Task 2: Group by department and project; sort projects by total time.
     */
    public static void task2_DepartmentProjectSorted(List<EmployeeWorkLog> logs) throws Exception {
        Map<String, Map<String, Double>> grouped = logs.stream()
            .collect(Collectors.groupingBy(
                EmployeeWorkLog::getDepartment,
                Collectors.groupingBy(
                    EmployeeWorkLog::getProjectId,
                    Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
                )
            ));

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"Department", "Project ID", "Total Hours"});
        grouped.forEach((dept, projectMap) -> {
            projectMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> {
                    rows.add(new String[]{dept, entry.getKey(), String.valueOf(entry.getValue())});
                });
        });
        CsvExporter.writeToCsv(rows, "task2_department_projects.csv");
        System.out.println(grouped);
    }

    /**
     * Task 3: Standard deviation of employee hours per project.
     */
    public static void task3_StdDevPerProject(List<EmployeeWorkLog> logs) throws Exception {
        Map<String, List<Double>> grouped = logs.stream()
            .collect(Collectors.groupingBy(
                EmployeeWorkLog::getProjectId,
                Collectors.mapping(EmployeeWorkLog::getHoursWorked, Collectors.toList())
            ));

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"Project ID", "Std Dev of Hours"});

        grouped.forEach((projectId, hoursList) -> {
            double mean = hoursList.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double variance = hoursList.stream()
                .mapToDouble(h -> Math.pow(h - mean, 2))
                .average().orElse(0);
            double stdDev = Math.sqrt(variance);
            rows.add(new String[]{projectId, String.valueOf(stdDev)});
        });

        CsvExporter.writeToCsv(rows, "task3_std_dev.csv");
        System.out.println(grouped);
    }

    /**
     * Task 4: "Bug Fix" tasks grouped by category and day of week.
     */
    public static void task4_BugFixGroupedByDay(List<EmployeeWorkLog> logs) throws Exception {
        Map<String, Map<DayOfWeek, List<EmployeeWorkLog>>> grouped = logs.stream()
            .filter(log -> log.getTaskCategory().equalsIgnoreCase("Bug Fix") && log.getDate() != null)
            .collect(Collectors.groupingBy(
                EmployeeWorkLog::getTaskCategory,
                Collectors.groupingBy(log -> log.getDate().getDayOfWeek())
            ));

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"Category", "Day of Week", "Employee ID", "Project ID", "Hours"});
        grouped.forEach((category, dayMap) -> {
            dayMap.forEach((day, logList) -> {
                for (EmployeeWorkLog log : logList) {
                    rows.add(new String[]{
                        category,
                        day.toString(),
                        log.getEmployeeId(),
                        log.getProjectId(),
                        String.valueOf(log.getHoursWorked())
                    });
                }
            });
        });

        CsvExporter.writeToCsv(rows, "task4_bug_fix.csv");
        System.out.println(grouped);
    }

    /**
     * Task 5: Engineering employees working on >2 projects: count unique projects.
     */
    public static void task5_EngineeringMultipleProjects(List<EmployeeWorkLog> logs) throws Exception {
        Map<String, Long> result = logs.stream()
            .filter(log -> log.getDepartment().equalsIgnoreCase("Engineering"))
            .collect(Collectors.groupingBy(
                EmployeeWorkLog::getEmployeeId,
                Collectors.mapping(EmployeeWorkLog::getProjectId, Collectors.toSet())
            ))
            .entrySet().stream()
            .filter(entry -> entry.getValue().size() > 2)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> (long) entry.getValue().size()
            ));

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"Employee ID", "Project Count"});
        result.forEach((empId, count) ->
            rows.add(new String[]{empId, String.valueOf(count)}));
        CsvExporter.writeToCsv(rows, "task5_engineering_multi_projects.csv");
        System.out.println(result);
    }
}



