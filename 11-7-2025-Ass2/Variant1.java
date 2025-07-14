package com.prana;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Variant1 {
    public static void findEngineeringMultiProjectEmployees(List<EmpWorkLog> logs) {
        String departmentName = "Devops"; 
        Map<String, Set<String>> employeeProjects = logs.stream()
                .filter(log -> log.getDepartment().equalsIgnoreCase(departmentName))
                .collect(Collectors.groupingBy(
                        EmpWorkLog::getEmployeeId,
                        Collectors.mapping(EmpWorkLog::getProjectId, Collectors.toSet())
                ));
        List<String> matchedEmployeeIds = employeeProjects.entrySet().stream()
                .filter(e -> e.getValue().size() > 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Matched employees count: " + matchedEmployeeIds.size());
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\LENOVO\\MedPlus\\variant1_output.csv"))) {
            writer.println("EmployeeId");
            for (String empId : matchedEmployeeIds) {
                writer.println(empId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Variant 1 CSV exported to: C:\\Users\\LENOVO\\MedPlus\\variant1_output.csv");
    }
}
