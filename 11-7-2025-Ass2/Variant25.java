package com.prana;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Variant25 {
    public static void sortDepartmentProjectDate(List<EmpWorkLog> logs) {
        List<EmpWorkLog> sorted = logs.stream()
                .sorted(Comparator.comparing(EmpWorkLog::getDepartment, Comparator.nullsLast(String::compareToIgnoreCase))
                        .thenComparing(EmpWorkLog::getProjectId, Comparator.nullsLast(String::compareToIgnoreCase))
                        .thenComparing(EmpWorkLog::getDate, Comparator.nullsLast(Comparator.naturalOrder())))
                        .collect(Collectors.toList());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.printf("%-12s %-15s %-20s %-12s %-12s %-15s %-12s %-20s%n",
                "EmployeeId", "Name", "Department", "ProjectId", "Date",
                "TaskCategory", "HoursWorked", "Remarks");
        System.out.println("---------------------------------------------------------------------------------------------");
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\LENOVO\\MedPlus\\variant25_output.csv"))) {
            writer.println("EmployeeId,Name,Department,ProjectId,Date,TaskCategory,HoursWorked,Remarks");
            for (EmpWorkLog log : sorted) {
                String dateStr = log.getDate() != null ? log.getDate().format(fmt) : "";
                System.out.printf("%-12s %-15s %-20s %-12s %-12s %-15s %-12.2f %-20s%n",
                        log.getEmployeeId(),
                        log.getName(),
                        log.getDepartment(),
                        log.getProjectId(),
                        dateStr,
                        log.getTaskCategory(),
                        log.getHoursWorked(),
                        log.getRemarks());
                writer.printf("%s,%s,%s,%s,%s,%s,%.2f,%s%n",
                        log.getEmployeeId(),
                        log.getName(),
                        log.getDepartment(),
                        log.getProjectId(),
                        dateStr,
                        log.getTaskCategory(),
                        log.getHoursWorked(),
                        log.getRemarks());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n Variant 25 CSV exported to: C:\\Users\\LENOVO\\MedPlus\\variant25_output.csv");
    }
}
