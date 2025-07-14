package com.prana;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Variant16 {
    public static void computeTimePercentagePerCategory(List<EmpWorkLog> logs) {
        Map<String, Map<String, Double>> grouped = logs.stream()
                .collect(Collectors.groupingBy(
                        EmpWorkLog::getEmployeeId,
                        Collectors.groupingBy(
                                EmpWorkLog::getTaskCategory,
                                Collectors.summingDouble(EmpWorkLog::getHoursWorked)
                        )
                ));
        List<String[]> resultRows = new ArrayList<>();
        resultRows.add(new String[]{"EmployeeId", "TaskCategory", "TotalHours", "Percentage"});
        System.out.printf("%-15s %-20s %-12s %-10s%n", "EmployeeId", "TaskCategory", "TotalHours", "Percentage");
        System.out.println("--------------------------------------------------------------");
        grouped.forEach((empId, categoryMap) -> {
            double empTotalHours = categoryMap.values().stream().mapToDouble(Double::doubleValue).sum();
            categoryMap.forEach((category, hours) -> {
                double percent = (hours / empTotalHours) * 100.0;
                resultRows.add(new String[]{
                        empId,
                        category,
                        String.format("%.2f", hours),
                        String.format("%.2f", percent)
                });
                System.out.printf("%-15s %-20s %-12.2f %-10.2f%n", empId, category, hours, percent);
            });
        });
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\LENOVO\\MedPlus\\variant16_output.csv"))) {
            for (String[] row : resultRows) {
                writer.println(String.join(",", row));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n Variant 16 CSV exported to: C:\\Users\\LENOVO\\MedPlus\\variant16_output.csv");
    }
}
