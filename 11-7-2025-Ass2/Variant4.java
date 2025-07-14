package com.prana;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class Variant4 {
    public static void computeAverageWeeklyHours(List<EmpWorkLog> logs) {
        Map<String, Map<YearMonth, List<EmpWorkLog>>> grouped = logs.stream()
                .filter(log -> log.getDate() != null)
                .collect(Collectors.groupingBy(
                        EmpWorkLog::getEmployeeId,
                        Collectors.groupingBy(
                                log -> YearMonth.of(log.getDate().getYear(), log.getDate().getMonth())
                        )
                ));
        List<String[]> resultRows = new ArrayList<>();
        resultRows.add(new String[]{"EmployeeId", "Year-Month", "Total Hours", "Avg Weekly Hours"});

        System.out.printf("%-15s %-10s %-12s %-17s%n", "EmployeeId", "Year-Month", "Total Hours", "Avg Weekly Hours");
        System.out.println("-------------------------------------------------------------");

        grouped.forEach((empId, monthMap) -> {
            monthMap.forEach((yearMonth, workLogs) -> {
                double totalHours = workLogs.stream().mapToDouble(EmpWorkLog::getHoursWorked).sum();
                int weeksInMonth = 4;
                double avgWeekly = totalHours / weeksInMonth;
                resultRows.add(new String[]{
                        empId,
                        yearMonth.toString(),
                        String.format("%.2f", totalHours),
                        String.format("%.2f", avgWeekly)
                });
                System.out.printf("%-15s %-10s %-12.2f %-17.2f%n", empId, yearMonth.toString(), totalHours, avgWeekly);
            });
        });
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\LENOVO\\MedPlus\\variant4_output.csv"))) {
            for (String[] row : resultRows) {
                writer.println(String.join(",", row));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n Variant 4 CSV exported to: C:\\Users\\LENOVO\\MedPlus\\variant4_output.csv");
    }
}
