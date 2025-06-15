package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.domain.EmployeePojo;

public class CalculateStdPerProject {
	public  void calculateStdDevPerProject(List<EmployeePojo> employeeList) {
		Map<String, List<EmployeePojo>> projectGroups = new HashMap<>();

		projectGroups = employeeList.stream().collect(Collectors.groupingBy(EmployeePojo::getProjectId));

		List<String> headers = Arrays.asList("Project", "Std Dev of Employee Hours");
		List<List<Object>> data = new ArrayList<>();

		projectGroups.forEach((projectId, employees) -> {
			Map<String, Double> employeeHours = employees.stream().collect(Collectors
					.groupingBy(EmployeePojo::getEmployeeId, Collectors.summingDouble(EmployeePojo::getHoursWorked)));

			List<Double> hoursList = new ArrayList<>(employeeHours.values());

			double mean = hoursList.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

			double stdDev = Math.sqrt(hoursList.stream().mapToDouble(h -> Math.pow(h - mean, 2)).average().orElse(0.0));

			data.add(Arrays.asList(projectId, stdDev));
		});

		WriteToExcel.writeToExcel("StdDevEmployeeHours", headers, data);
	}

}

