package com.prana;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

public class Variant11 {
    public static void findEmployeesWith3PlusCategoriesPerWeek(List<EmpWorkLog> logs) {
        Map<String, Map<String, List<EmpWorkLog>>> grouped = logs.stream()
                .filter(log -> log.getDate() != null)
                .collect(Collectors.groupingBy(
                        EmpWorkLog::getEmployeeId,
                        Collectors.groupingBy(log -> {
                            LocalDate date = log.getDate();
                            WeekFields weekFields = WeekFields.ISO;
                            int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
                            int year = date.getYear();
                            return year + "-W" + weekNumber;
                        })
                ));

        List<String[]> resultRows = new ArrayList<>();
        resultRows.add(new String[]{"EmployeeId", "Year-Week", "CategoriesCount"});

        System.out.printf("%-15s %-10s %-17s%n", "EmployeeId", "Year-Week", "CategoriesCount");
        System.out.println("--------------------------------------------");

        grouped.forEach((empId, weekMap) -> {
            weekMap.forEach((yearWeek, workLogs) -> {
                long categoryCount = workLogs.stream()
                        .map(EmpWorkLog::getTaskCategory)
                        .filter(Objects::nonNull)
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .distinct()
                        .count();

                if (categoryCount >= 3) {
                    resultRows.add(new String[]{empId, yearWeek, String.valueOf(categoryCount)});
                    System.out.printf("%-15s %-10s %-17d%n", empId, yearWeek, categoryCount);
                }
            });
        });
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\LENOVO\\MedPlus\\variant11_output.csv"))) {
            for (String[] row : resultRows) {
                writer.println(String.join(",", row));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n Variant 11 CSV exported to: C:\\Users\\LENOVO\\MedPlus\\variant11_output.csv");
    }
}

