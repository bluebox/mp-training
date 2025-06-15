package com.service;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.domain.EmployeePojo;

public  class Caliculate7DaysSlidingWindow {
	

	public  void calculate7DaySlidingAverage(List<EmployeePojo> employeeList) {
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

		WriteToExcel.writeToExcel("SevenDaySlidingAverage", headers, data);
	}
}
