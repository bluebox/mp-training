package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.domain.EmployeePojo;

public class TimeConsumingWork {
	public  Map<String, List<EmployeePojo>> timeConsumingWork(List<EmployeePojo> employeeList) {
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

		WriteToExcel.writeToExcel("TopTimeConsumingTasks", headers, data);
		return groupedEmployees;
	}
}
