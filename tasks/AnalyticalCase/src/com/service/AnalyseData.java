package com.service;

import com.domain.EmployeePojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnalyseData {
    public static WriteToExcel writeToExcel = new WriteToExcel();

    public static Map<String, List<EmployeePojo>> timeConsumingWork(EmployeeDataAnalytics employeeDataAnalytics) {
        Map<String, List<EmployeePojo>> groupedEmployees = new HashMap<>();
        
        for (EmployeePojo emp : employeeDataAnalytics.getEmployeeList()) {
            String dept = emp.getDepartment();
            if (!groupedEmployees.containsKey(dept)) {
                groupedEmployees.put(dept, new ArrayList<>());
            }
            groupedEmployees.get(dept).add(emp);
        }

        List<String> headers = Arrays.asList("Department", "Task", "Hours", "Employee");
        List<List<Object>> data = new ArrayList<>();
        
        for (String dept : groupedEmployees.keySet()) {
            List<EmployeePojo> employees = groupedEmployees.get(dept);
            employees.sort((e1, e2) -> Double.compare(e2.getHoursWorked(), e1.getHoursWorked()));
            
            int count = 0;
            for (EmployeePojo emp : employees) {
                if (count < 3) {
                    data.add(Arrays.asList(dept, emp.getTask(), emp.getHoursWorked(), emp.getName()));
                    count++;
                } else {
                    break;
                }
            }
        }
        
        writeToExcel.writeToExcel("TopTimeConsumingTasks", headers, data);
        return groupedEmployees;
    }

    public static void calculateWeeklyEffort(List<EmployeePojo> empList) {
        Map<String, Double> weeklyEffort = new HashMap<>();
        
        for (EmployeePojo emp : empList) {
            LocalDate date = emp.getDate().toLocalDate();
            int year = date.getYear();
            int week = date.getDayOfYear() / 7 + 1;
            String key = emp.getProjectId() + "_W" + week + "_" + year;
            
            weeklyEffort.put(key, weeklyEffort.getOrDefault(key, 0.0) + emp.getHoursWorked());
        }

        List<String> headers = Arrays.asList("Project_Week", "Total Hours");
        List<List<Object>> data = new ArrayList<>();
        
        for (Map.Entry<String, Double> entry : weeklyEffort.entrySet()) {
            data.add(Arrays.asList(entry.getKey(), entry.getValue()));
        }
        
        writeToExcel.writeToExcel("WeeklyEffortPerProject", headers, data);
    }

    public static void calculate7DaySlidingAverage(List<EmployeePojo> empList) {
        Map<LocalDate, List<Double>> dateToHours = new HashMap<>();
        
        for (EmployeePojo emp : empList) {
            LocalDate date = emp.getDate().toLocalDate();
            if (!dateToHours.containsKey(date)) {
                dateToHours.put(date, new ArrayList<>());
            }
            dateToHours.get(date).add(emp.getHoursWorked());
        }

        List<LocalDate> allDates = new ArrayList<>(dateToHours.keySet());
        allDates.sort((d1, d2) -> d1.compareTo(d2));

        List<String> headers = Arrays.asList("Date", "7-Day Avg Hours");
        List<List<Object>> data = new ArrayList<>();
        
        for (LocalDate currentDate : allDates) {
            LocalDate startWindow = currentDate.minusDays(6);
            double totalHours = 0.0;
            int count = 0;
            
            for (LocalDate date : allDates) {
                if (!date.isBefore(startWindow) && !date.isAfter(currentDate)) {
                    List<Double> hoursList = dateToHours.get(date);
                    for (Double hours : hoursList) {
                        totalHours += hours;
                        count++;
                    }
                }
            }
            
            double average = count == 0 ? 0.0 : totalHours / count;
            data.add(Arrays.asList(currentDate.toString(), average));
        }
        
        writeToExcel.writeToExcel("SevenDaySlidingAverage", headers, data);
    }

    public static void calculateProjectProductivity(List<EmployeePojo> employeeList) {
        Map<String, List<EmployeePojo>> projectMap = new HashMap<>();
        
        for (EmployeePojo emp : employeeList) {
            String projectId = emp.getProjectId();
            if (!projectMap.containsKey(projectId)) {
                projectMap.put(projectId, new ArrayList<>());
            }
            projectMap.get(projectId).add(emp);
        }

        List<String> headers = Arrays.asList("Project", "Total Hours", "Avg Hours/Employee");
        List<List<Object>> data = new ArrayList<>();
        
        for (Map.Entry<String, List<EmployeePojo>> entry : projectMap.entrySet()) {
            String projectId = entry.getKey();
            List<EmployeePojo> employees = entry.getValue();
            
            double totalHours = 0.0;
            for (EmployeePojo emp : employees) {
                totalHours += emp.getHoursWorked();
            }
            
            Set<String> uniqueEmployees = new HashSet<>();
            for (EmployeePojo emp : employees) {
                uniqueEmployees.add(emp.getEmployeeId());
            }
            
            double avgHours = uniqueEmployees.isEmpty() ? 0.0 : totalHours / uniqueEmployees.size();
            data.add(Arrays.asList(projectId, totalHours, avgHours));
        }
        
        writeToExcel.writeToExcel("ProjectProductivity", headers, data);
    }

    public static void calculateStdDevPerProject(List<EmployeePojo> empList) {
        Map<String, List<EmployeePojo>> projectGroups = new HashMap<>();
        
        for (EmployeePojo emp : empList) {
            String projectId = emp.getProjectId();
            if (!projectGroups.containsKey(projectId)) {
                projectGroups.put(projectId, new ArrayList<>());
            }
            projectGroups.get(projectId).add(emp);
        }

        List<String> headers = Arrays.asList("Project", "Std Dev of Employee Hours");
        List<List<Object>> data = new ArrayList<>();
        
        for (String projectId : projectGroups.keySet()) {
            List<EmployeePojo> employees = projectGroups.get(projectId);
            
            Map<String, Double> employeeHours = new HashMap<>();
            for (EmployeePojo emp : employees) {
                String empId = emp.getEmployeeId();
                employeeHours.put(empId, employeeHours.getOrDefault(empId, 0.0) + emp.getHoursWorked());
            }
            
            List<Double> hoursList = new ArrayList<>(employeeHours.values());
            double sum = 0.0;
            for (Double hours : hoursList) {
                sum += hours;
            }
            double mean = hoursList.isEmpty() ? 0.0 : sum / hoursList.size();
            
            double sumSquaredDiff = 0.0;
            for (Double hours : hoursList) {
                sumSquaredDiff += Math.pow(hours - mean, 2);
            }
            double stdDev = hoursList.isEmpty() ? 0.0 : Math.sqrt(sumSquaredDiff / hoursList.size());
            
            data.add(Arrays.asList(projectId, stdDev));
        }
        
        writeToExcel.writeToExcel("StdDevEmployeeHours", headers, data);
    }
}