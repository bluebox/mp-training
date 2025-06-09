package com.service;

import com.domain.EmployeePojo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AnalyseData {
	public static WriteToExcel writeToExcel = new WriteToExcel();

	public static Map<String, List<EmployeePojo>> timeConsumingWork(List<EmployeePojo> employeeList) {
		Map<String, List<EmployeePojo>> groupedEmployees = new HashMap<>();

		groupedEmployees = employeeList.stream().collect(Collectors.groupingBy(EmployeePojo::getDepartment));
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

	public static void calculateWeeklyEffort(List<EmployeePojo> employeeList) {
		Map<String, Double> weeklyEffort = new HashMap<>();

		weeklyEffort = employeeList.stream().collect(Collectors.groupingBy(emp -> {
			LocalDate date = emp.getDate();
			int year = date.getYear();
			int week = date.getDayOfYear() / 7 + 1;
			return emp.getProjectId() + "_W" + week + "_" + year;
		}, Collectors.summingDouble(EmployeePojo::getHoursWorked)));

		List<String> headers = Arrays.asList("Project_Week", "Total Hours");
		List<List<Object>> data = new ArrayList<>();

		for (Map.Entry<String, Double> entry : weeklyEffort.entrySet()) {
			data.add(Arrays.asList(entry.getKey(), entry.getValue()));
		}

		writeToExcel.writeToExcel("WeeklyEffortPerProject", headers, data);
	}

	public static void calculate7DaySlidingAverage(List<EmployeePojo> employeeList) {
		Map<LocalDate, List<Double>> dateToHours = new HashMap<>();
		dateToHours = employeeList.stream().collect(Collectors.groupingBy(EmployeePojo::getDate,
				Collectors.mapping(EmployeePojo::getHoursWorked, Collectors.toList())));

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

		projectMap = employeeList.stream()
				.collect(Collectors.groupingBy(EmployeePojo::getProjectId));

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
		List<String> headers = Arrays.asList("Project", "Total Hours", "Avg Hours/Employee");

		writeToExcel.writeToExcel("ProjectProductivity", headers, data);
	}

	public static void calculateStdDevPerProject(List<EmployeePojo> employeeList) {
		Map<String, List<EmployeePojo>> projectGroups = new HashMap<>();

		projectGroups = employeeList.stream()
				.collect(Collectors.groupingBy(EmployeePojo::getProjectId));
		

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