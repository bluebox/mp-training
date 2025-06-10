package com.employee.service;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.domain.Employee;

public class EmployeeService {

	SaveToCSV save = new SaveToCSV();
	private Map<String, List<Employee>> employees;

	public void readEmployeesFromExcel(String filePath) {
		employees = new HashMap<>();
		System.out.println(filePath);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			// Skiping header row
			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				String id = row.getCell(0).getStringCellValue();
				String name = row.getCell(1).getStringCellValue();
				String department = row.getCell(2).getStringCellValue();
				String projectId = row.getCell(3).getStringCellValue();

				Cell dateCell = row.getCell(4);
				LocalDate date = LocalDate.parse(dateCell.getStringCellValue(), formatter);

				String taskCategory = row.getCell(5).getStringCellValue();
				double hoursWorked = row.getCell(6).getNumericCellValue();
				String remark = row.getCell(7).getStringCellValue();

				Employee emp = new Employee(id, name, department, projectId, date, taskCategory, hoursWorked, remark);

				if (employees.containsKey(id)) {
					employees.get(id).add(emp);

				} else {
					employees.put(id, new ArrayList<Employee>());
					employees.get(id).add(emp);

				}
			}

		} catch (Exception e) {
			System.out.println("File not found Quiting the application");
			System.exit(0);
		}

		// System.out.println(employees);
	}

	// Task 1
	public void task1() {

		Map<String, List<Employee>> overworkedEmployees = employees.entrySet().stream()
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().stream()
						.filter(emp -> emp.getHoursWorked() > 9).collect(Collectors.toList())));

		overworkedEmployees.entrySet().removeIf(entry -> entry.getValue().isEmpty());
		List<String[]> reportRows = new ArrayList<>();
		reportRows.add(new String[] { "Employee ID", "Name", "Date", "Hours Worked", "Task" });

		overworkedEmployees.forEach((department, employeeList) -> {
			for (Employee emp : employeeList) {
				reportRows.add(new String[] { emp.getId(), emp.getName(), emp.getDate().toString(),
						String.valueOf(emp.getHoursWorked()), emp.getTaskCategory() });
			}
		});

		// System.out.println(overworkedLogs);
	}

	// Task 2
	public void task2() {
		Map<String, Map<java.time.DayOfWeek, List<Employee>>> bugFixGroup = employees.values().stream()
				.flatMap(List::stream).filter(e -> e.getTaskCategory().equals("Bug Fix")).collect(Collectors
						.groupingBy(Employee::getTaskCategory, Collectors.groupingBy(e -> e.getDate().getDayOfWeek())));
		// System.out.println(bugFixGrouped);

		List<String[]> rows = new ArrayList<>();
		rows.add(new String[] { "Category", "DayOfWeek", "Employee ID", "Date", "Hours" });

		bugFixGroup.forEach((category, dayMap) -> {
			dayMap.forEach((day, logs) -> {
				for (Employee e : logs) {
					rows.add(new String[] { category, day.toString(), e.getId(), e.getDate().toString(),
							String.valueOf(e.getHoursWorked()) });
				}
			});
		});

		save.writeToCSV("Task2_BugFixGrouped.csv", rows);
	}

	// task 3
	public void task3() {
		Map<String, Map<String, Double>> projectCategoryHours = employees.values().stream().flatMap(List::stream)
				.collect(Collectors.groupingBy(Employee::getProjectId, Collectors.groupingBy(Employee::getTaskCategory,
						Collectors.summingDouble(Employee::getHoursWorked))));

		Map<String, Map<String, Double>> categoryPercent = new HashMap<>();

		for (Map.Entry<String, Map<String, Double>> entry : projectCategoryHours.entrySet()) {
			String projectId = entry.getKey();
			Map<String, Double> hoursByCategory = entry.getValue();

			double totalHours = hoursByCategory.values().stream().mapToDouble(Double::doubleValue).sum();

			Map<String, Double> percentByCategory = new HashMap<>();
			for (Map.Entry<String, Double> categoryEntry : hoursByCategory.entrySet()) {
				double percentage = (categoryEntry.getValue() / totalHours) * 100;
				percentByCategory.put(categoryEntry.getKey(), percentage);
			}

			categoryPercent.put(projectId, percentByCategory);
		}

		List<String[]> rows = new ArrayList<>();
		rows.add(new String[] { "Project ID", "Task Category", "Contribution %" });

		categoryPercent.forEach((projectId, categoryMap) -> {
			categoryMap.forEach((category, percent) -> {
				rows.add(new String[] { projectId, category, String.format("%.2f", percent)

				});
			});
		});

		save.writeToCSV("Task3_CategoryContribution.csv", rows);
	}

	// task 4
	public void task4() {

		List<String[]> rows = new ArrayList<>();
		rows.add(new String[] { "Employee ID" });

		for (Map.Entry<String, List<Employee>> entry : employees.entrySet()) {
			String empId = entry.getKey();
			List<Employee> empLogs = entry.getValue();

			Map<String, Set<String>> projectsByMonth = empLogs.stream()
					.collect(Collectors.groupingBy(emp -> emp.getDate().getYear() + "-" + emp.getDate().getMonthValue(),
							Collectors.mapping(Employee::getProjectId, Collectors.toSet())));
			boolean switchedProject = projectsByMonth.values().stream().anyMatch(projectSet -> projectSet.size() > 1);

			if (switchedProject) {
				rows.add(new String[] { empId });
			}
		}
		save.writeToCSV("Task4_ProjectSwitchers.csv", rows);

	}

	// task 5

	public void task5() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Employee ID: ");
		String empId = scanner.nextLine();
		scanner.close();
		List<Employee> employeeLogs = employees.get(empId);
		if (employeeLogs == null) {
			System.out.println("User Not found");
			return;
		}
		if (employeeLogs.size() < 7) {
			System.out.println("Employee hasn't worked for last seven days" + empId);
			return;
		}
		List<Employee> sortedLogs = employeeLogs.stream().sorted(Comparator.comparing(Employee::getDate))
				.collect(Collectors.toList());

		double average = 0;
		for (int j = sortedLogs.size() - 8; j < sortedLogs.size(); j++) {
			average += sortedLogs.get(j).getHoursWorked();
			// System.out.println(average);
		}
		average /= 7;

		System.out.printf("Last 7-day average hours worked for employee %s: %.2f%n", empId, average);
		List<String[]> rows = new ArrayList<>();
		rows.add(new String[] { "Employee ID", "Last 7-Days Average" });
		rows.add(new String[] { empId, String.format("%.2f", average) });

		String fileName = "Task5_Last7DayAverage_" + empId + ".csv";
		save.writeToCSV(fileName, rows);
	}

}
