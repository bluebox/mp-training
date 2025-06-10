package EmployeesAnalysisConsoleApp.src;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class AnalysingExcelData {

	// Query-1. Sort by task category and hours descending, top 3 time-consuming
	// tasks per department.
	public static Map<String, List<EmployeesWorkLogPOJO>> getTopTasksPerDepartment(
			List<EmployeesWorkLogPOJO> employeeLogs) {
		Map<String, List<EmployeesWorkLogPOJO>> departmentToLogsMap = employeeLogs.stream()
				.collect(Collectors.groupingBy(EmployeesWorkLogPOJO::getDepartment));

		Map<String, List<EmployeesWorkLogPOJO>> queryResult = new HashMap<>();

		for (Map.Entry<String, List<EmployeesWorkLogPOJO>> mapEntry : departmentToLogsMap.entrySet()) {
			String department = mapEntry.getKey();
			List<EmployeesWorkLogPOJO> departmentLogs = mapEntry.getValue();

			List<EmployeesWorkLogPOJO> topThreeTasks = departmentLogs.stream()
					.sorted(Comparator.comparing(EmployeesWorkLogPOJO::getTaskCategory)
							.thenComparing(Comparator.comparing(EmployeesWorkLogPOJO::getHoursWorked).reversed()))
					.limit(3).collect(Collectors.toList());

			queryResult.put(department, topThreeTasks);
		}
		return queryResult;
	}

	// Query-2. Weekend logs, summarize weekend hours.
	public static double computeWeekendWorkHours(List<EmployeesWorkLogPOJO> employeeLogs) {
		double[] totalWeekendHours = { 0.0 };
		employeeLogs.stream().forEach(employeeLog -> {
			if (employeeLog.getDate().getDayOfWeek() == DayOfWeek.SATURDAY
					|| employeeLog.getDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
				totalWeekendHours[0] += employeeLog.getHoursWorked();
			}
		});
		return totalWeekendHours[0];

//		return employeeLogs.stream().filter(employeeLog -> {
//			DayOfWeek day = employeeLog.getDate().getDayOfWeek();
//			return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
//		}).map(EmployeesWorkLogPOJO::getHoursWorked).reduce(0.0, Double::sum);
	}

	// Query-3. Daily average hours trend in last 30 working days per employee.
	public static Map<String, Double> getDailyAverageWorkingHours30DaysPerEmployee(
			List<EmployeesWorkLogPOJO> employeeLogs) {
		LocalDate thirtyDaysBefore = LocalDate.now().minusDays(30);

		return employeeLogs.stream().filter(employeeLog -> !employeeLog.getDate().isBefore(thirtyDaysBefore))
				.collect(Collectors.groupingBy(EmployeesWorkLogPOJO::getEmployeeID,
						Collectors.averagingDouble(EmployeesWorkLogPOJO::getHoursWorked)));

	}

	// Query-4. Days with <2 hours, group by employee and date.
	public static Map<String, List<LocalDate>> getLessthanTwoHourWorkDaysData(List<EmployeesWorkLogPOJO> employeeLogs) {
		Map<String, List<LocalDate>> queryResult = new HashMap<>();
//		employeeLogs.stream().filter(employeeLog -> employeeLog.getHoursWorked() < 2.0).forEach(employeeLog -> {
//			queryResult.computeIfAbsent(employeeLog.getEmployeeID(), k -> new ArrayList<>()).add(employeeLog.getDate());
//		});

		queryResult = employeeLogs.stream().filter(employeeLog -> employeeLog.getHoursWorked() < 2.0)
				.collect(Collectors.groupingBy(EmployeesWorkLogPOJO::getEmployeeID,
						Collectors.mapping(EmployeesWorkLogPOJO::getDate, Collectors.toList())));
		return queryResult;
	}

	// Query-5. Time % spent in each task category per employee
	public static Map<String, Map<String, Double>> getPercentTimeSpentTaskCategoryWisePerEmployee(
			List<EmployeesWorkLogPOJO> employeeLogs) {
		Map<String, Map<String, Double>> groupedByEIDandTaskMap = new HashMap<>();

		Map<String, List<EmployeesWorkLogPOJO>> logsGroupedByEmployee = employeeLogs.stream()
				.collect(Collectors.groupingBy(EmployeesWorkLogPOJO::getEmployeeID));

		for (Map.Entry<String, List<EmployeesWorkLogPOJO>> entry : logsGroupedByEmployee.entrySet()) {
			groupedByEIDandTaskMap.put(entry.getKey(), getTimeSpentByEmployee(entry.getValue()));
		}
		return groupedByEIDandTaskMap;
	}

	private static Map<String, Double> getTimeSpentByEmployee(List<EmployeesWorkLogPOJO> workLogs) {
		Double totalTimeSpent = workLogs.stream().mapToDouble(EmployeesWorkLogPOJO::getHoursWorked).sum();
		Map<String, Double> timeSpentPerCategory = workLogs.stream().collect(Collectors.groupingBy(
				EmployeesWorkLogPOJO::getTaskCategory, Collectors.summingDouble(EmployeesWorkLogPOJO::getHoursWorked)));
		return timeSpentPerCategory.entrySet().stream().collect(Collectors.toMap(Entry::getKey, entry -> {
			return entry.getValue() / totalTimeSpent * 100;
		}));
	}

	// Query-6: Group by Employee and month; compute average weekly hours;
	public static Map<String, Map<YearMonth, Double>> getAverageWeeklyHoursGroupByEmployeeAndMonth(
			List<EmployeesWorkLogPOJO> workLogs) {
		
		//Grouping done by employeeID
		Map<String, List<EmployeesWorkLogPOJO>> workLogsGroupedByEmployeeID = workLogs.stream()
				.collect(Collectors.groupingBy(EmployeesWorkLogPOJO::getEmployeeID));
		
		Map<String, Map<YearMonth, List<EmployeesWorkLogPOJO>>> employeeIDGroupedWorkLogsGroupedByMonth = new HashMap<>();
		
		//Further grouping of employeeID grouped data by Month
		workLogsGroupedByEmployeeID.forEach((employeeID, employeeWorkLogs) -> {
			Map<YearMonth, List<EmployeesWorkLogPOJO>> employeeWorkLogsGroupedByMonth = employeeWorkLogs.stream()
					.collect(Collectors.groupingBy(employeeWorkLog -> YearMonth.from(employeeWorkLog.getDate())));
			
			employeeIDGroupedWorkLogsGroupedByMonth.put(employeeID, employeeWorkLogsGroupedByMonth);
		});
		
		Map<String,Map<YearMonth, Double>> queryResult = new HashMap<>();
		for(Map.Entry<String,Map<YearMonth, List<EmployeesWorkLogPOJO>>> entry: employeeIDGroupedWorkLogsGroupedByMonth.entrySet())
		{
			Map<YearMonth, Double> monthlyHoursAverage = entry.getValue().entrySet().stream().collect(Collectors.toMap(Entry::getKey, currentEntry -> {
				return (currentEntry.getValue().stream().map(EmployeesWorkLogPOJO::getHoursWorked).mapToDouble(Double::doubleValue).sum()/noOfWeeksInMonth(currentEntry.getKey()));
			}));
			queryResult.put(entry.getKey(), monthlyHoursAverage);
		};
		return queryResult;
	}
	
	private static int noOfWeeksInMonth(YearMonth month) {
		return month.getMonth().minLength() / 7;
	}
}
