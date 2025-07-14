package com.prana;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Variant21 {
    public static void findCriticalProjects(List<EmpWorkLog> logs) {
        Map<String, List<EmpWorkLog>> grouped = logs.stream()
                .collect(Collectors.groupingBy(EmpWorkLog::getProjectId));
        List<String[]> resultRows = new ArrayList<>();
        resultRows.add(new String[]{"ProjectId","EmployeeCount","TotalHours"});
        System.out.printf("%-15s %-15s %-12s%n","ProjectId","EmployeeCount","TotalHours");
        System.out.println("----------------------------------------------");
        grouped.forEach((projectId, projectLogs) -> {
            Set<String> uniqueEmployees = projectLogs.stream()
                    .map(EmpWorkLog::getEmployeeId)
                    .collect(Collectors.toSet());
            double totalHours = projectLogs.stream()
                    .mapToDouble(EmpWorkLog::getHoursWorked)
                    .sum();
            if (uniqueEmployees.size()>5 && totalHours>200) {
                resultRows.add(new String[]{
                        projectId,
                        String.valueOf(uniqueEmployees.size()),
                        String.format("%.2f", totalHours)
                });
                System.out.printf("%-15s %-15d %-12.2f%n", projectId, uniqueEmployees.size(), totalHours);
            }
        });
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\LENOVO\\MedPlus\\variant21_output.csv"))) {
            for (String[] row : resultRows) {
                writer.println(String.join(",", row));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n Variant 21 CSV exported to: C:\\Users\\LENOVO\\MedPlus\\variant21_output.csv");
    }
}
