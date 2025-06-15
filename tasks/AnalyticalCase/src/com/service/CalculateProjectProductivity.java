package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.domain.EmployeePojo;

public class CalculateProjectProductivity {


	public  void calculateProjectProductivity(List<EmployeePojo> employeeList) {
		Map<String, List<EmployeePojo>> projectMap = new HashMap<>();

		projectMap = employeeList.stream().collect(Collectors.groupingBy(EmployeePojo::getProjectId));

		List<List<Object>> data = new ArrayList<>();
		projectMap.forEach((projectId, employees) -> {
			double totalHours = employees.stream().mapToDouble(EmployeePojo::getHoursWorked).sum();

			long uniqueEmployeeCount = employees.stream().map(EmployeePojo::getEmployeeId).distinct().count();

			double avgHours = uniqueEmployeeCount == 0 ? 0.0 : totalHours / uniqueEmployeeCount;

			data.add(Arrays.asList(projectId, totalHours, avgHours));
		});

		List<String> headers = Arrays.asList("Project", "Total Hours", "Avg Hours/Employee");

		WriteToExcel.writeToExcel("ProjectProductivity", headers, data);
	}
}
