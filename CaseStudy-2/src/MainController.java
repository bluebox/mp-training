package com.employee.controller;

import com.employee.dao.EmployeeDataToCSV;
import com.employee.dao.ReadEmployeeData;
import com.employee.model.EmployeeWorkLog;
import com.employee.service.VarientServiceImpl;

import java.util.List;
import java.util.Map;

public class MainController {
    public static void main(String[] args) {
        ReadEmployeeData reader = new ReadEmployeeData();
        List<EmployeeWorkLog> logs1 = reader.readEmployeeData("C:/Users/sruth/eclipse-workspace/employee/EmployeeDetails1.xlsx");
        List<EmployeeWorkLog> logs2 = reader.readEmployeeData("C:/Users/sruth/eclipse-workspace/employee/EmployeeDetails2.xlsx");

        EmployeeDataToCSV exporter = new EmployeeDataToCSV();
        exporter.writeToCSV(logs1, "EmployeeData.csv");

        // Variant 2
        Map<String, List<EmployeeWorkLog>> deptTasks = VarientServiceImpl.timeConsuming_PerDepartment(logs1);
        deptTasks.forEach((dept, tasks) -> {
            System.out.println("Department: " + dept);
            tasks.forEach(System.out::println);
        });

        // Variant 9
        System.out.println("Top 5 Employees in last 60 days:");
        VarientServiceImpl.top5EmployeesLast60Days(logs1).forEach(entry ->
                System.out.println(entry.getKey() + " => " + entry.getValue() + " hours"));

        // Variant 22
        System.out.println("\nHour Differences between two sheets:");
        VarientServiceImpl.computeHourDifferences(logs1, logs2).forEach((key, value) ->
                System.out.println(key + " => " + value + " hrs"));

        // Variant 27
        System.out.println("\nGrouped by Time Period:");
        VarientServiceImpl.groupByTimePeriod(logs1).forEach((period, group) -> {
            System.out.println("\n" + period + ":");
            group.forEach(System.out::println);
        });

        // Variant 30
        double meetingTime = VarientServiceImpl.totalMeetingTime(logs1);
        System.out.println("\nTotal Meeting Time: " + meetingTime + " hrs");
    }
}