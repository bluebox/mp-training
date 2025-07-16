package com.dom.employeelogproject1;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

public class tasks {

    private static void writeLinesToCSV(List<String[]> lines, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {

            for (String[] line : lines) {
                String csvLine = String.join(",", line);
                writer.append(csvLine).append("\n");
                System.out.println(csvLine);  
            }

            System.out.println(" File saved " + filename );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void task1_UrgentOrCriticalLogs(List<EmployeeLog> logs) {
        System.out.println(" Urgent or Critical Logs Sorted by Name");

        List<EmployeeLog> filtered = logs.stream()
                .filter(log -> log.getRemarks().toLowerCase().contains("#urgent") || log.getRemarks().toLowerCase().contains("#critical"))
                .sorted(Comparator.comparing(EmployeeLog::getName))
                .collect(Collectors.toList());

        List<String[]> lines = new ArrayList<>();
        lines.add(new String[]{"EmployeeId", "Name", "Department", "ProjectId", "Date", "TaskCategory", "HoursWorked", "Remarks"});

        for (EmployeeLog log : filtered) {
            lines.add(new String[]{
                    log.getEmployeeId(),
                    log.getName(),
                    log.getDepartment(),
                    log.getProjectId(),
                    log.getDate().toString(),
                    log.getTaskCategory(),
                    String.valueOf(log.getHoursWorked()),
                    log.getRemarks()
            });
        }

        writeLinesToCSV(lines, "urgent_critical_logs.csv");
    }

    public static void task2_WeeklyEffortPerProject(List<EmployeeLog> logs) {
        System.out.println(" Weekly Effort Per Project");

        Map<String, Double> projectWeekEfforts = logs.stream()
                .collect(Collectors.groupingBy(
                        EmployeeLog::getProjectId,
                        
                                Collectors.summingDouble(EmployeeLog::getHoursWorked)
                        
                ));

        List<String[]> lines = new ArrayList<>();
        lines.add(new String[]{"ProjectId", "TotalHours"});

       projectWeekEfforts.forEach((project,hours) -> {
               lines.add(new String[]{project, String.valueOf(hours)});
           
        });

        writeLinesToCSV(lines, "weekly_effort.csv");
    }

    public static void task3_TrackDepartmentSwitches(List<EmployeeLog> logs) {
        System.out.println(" Department Switches Mid-Month");

        Map<String, Map<String, Set<String>>> empMonthDepartments = logs.stream()
                .collect(Collectors.groupingBy(
                        EmployeeLog::getEmployeeId,
                        Collectors.groupingBy(
                                log -> log.getDate().getYear() + "-" + log.getDate().getMonthValue(),
                                Collectors.mapping(EmployeeLog::getDepartment, Collectors.toSet())
                        )
                ));

        List<String[]> lines = new ArrayList<>();
        lines.add(new String[]{"EmployeeId", "Month", "Departments"});

        empMonthDepartments.forEach((empId, monthDeptMap) -> {
            monthDeptMap.forEach((month, depts) -> {
                if (depts.size() > 1) {
                    lines.add(new String[]{empId, month, String.join(" | ", depts)});
                }
            });
        });

        writeLinesToCSV(lines, "department_switches.csv");
    }

    public static void task4_SummarizeWeekendHours(List<EmployeeLog> logs) {
        System.out.println(" Weekend Hours Summary");

        Map<String, Double> weekendHours = logs.stream()
                .filter(log -> {
                    DayOfWeek day = log.getDate().getDayOfWeek();
                    return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
                })
                .collect(Collectors.groupingBy(EmployeeLog::getEmployeeId, Collectors.summingDouble(EmployeeLog::getHoursWorked)));

        List<String[]> lines = new ArrayList<>();
        lines.add(new String[]{"EmployeeId", "WeekendHours"});

        weekendHours.forEach((empId, hours) -> lines.add(new String[]{empId, String.valueOf(hours)}));

        writeLinesToCSV(lines, "weekend_hours.csv");
    }

    public static void task5_ExtractTagsAndCount(List<EmployeeLog> logs) {
        System.out.println("Tag Counts");

        Map<String, Long> tagCounts = logs.stream()
                .flatMap(log -> Arrays.stream(log.getRemarks().split("\\s+")))
                .filter(word -> word.startsWith("#"))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        List<String[]> lines = new ArrayList<>();
        lines.add(new String[]{"Tag", "Count"});

        tagCounts.forEach((tag, count) -> lines.add(new String[]{tag, String.valueOf(count)}));

        writeLinesToCSV(lines, "tag_counts.csv");
    }
}
