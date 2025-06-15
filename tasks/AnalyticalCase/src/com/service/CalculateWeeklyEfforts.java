package com.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.domain.EmployeePojo;

public class CalculateWeeklyEfforts {

	public  void calculateWeeklyEffort(List<EmployeePojo> employeeList) {
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

		WriteToExcel.writeToExcel("WeeklyEffortPerProject", headers, data);
	}
}
