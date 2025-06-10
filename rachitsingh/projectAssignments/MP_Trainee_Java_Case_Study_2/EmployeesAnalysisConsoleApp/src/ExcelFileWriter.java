package EmployeesAnalysisConsoleApp.src;

import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelFileWriter {
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void writeTopThreeTasks(Map<String, List<EmployeesWorkLogPOJO>> data, String filePathName) {
		try (FileWriter fileWriter = new FileWriter(filePathName)) {
			fileWriter.write("Department,Employee ID,Name,Task Category,Hours Worked,Date\n");
			for (Map.Entry<String, List<EmployeesWorkLogPOJO>> mapEntry : data.entrySet()) {
				for (EmployeesWorkLogPOJO employeeLog : mapEntry.getValue()) {
					StringBuilder record = new StringBuilder();
					record.append(mapEntry.getKey()).append(",").append(employeeLog.getEmployeeID()).append(",")
							.append(employeeLog.getemployeeName()).append(",").append(employeeLog.getTaskCategory())
							.append(",").append(employeeLog.getHoursWorked()).append(",")
							.append(employeeLog.getDate().format(DATE_FORMAT)).append("\n");
					fileWriter.write(record.toString());
				}
			}
		} catch (IOException IOE) {
			System.err.println("Error occured while writing top 3 tasks: " + IOE.getMessage());
		}
	}

	public static void writeWeekendSummary(double totalHours, String filePathName) {
		try (FileWriter fileWriter = new FileWriter(filePathName)) {
			fileWriter.write("Total hours worked in the weeked:\n");
			fileWriter.write(String.valueOf(totalHours) + "\n");
		} catch (IOException IOE) {
			System.err.println("Error occured while writing the summary of weekend: " + IOE.getMessage());
		}
	}

	public static void writeDailyAverage(Map<String, Double> data, String filePathName) {
		try (FileWriter fileWriter = new FileWriter(filePathName)) {
			fileWriter.write("Employee ID, Average Hours Worked (Last 30 days)\n");
			for (Map.Entry<String, Double> mapEntry : data.entrySet()) {
				fileWriter.write(mapEntry.getKey() + "," + mapEntry.getValue() + "\n");
			}
		} catch (IOException IOE) {
			System.err.println("Error occured while writing daily average working hours: " + IOE.getMessage());
		}
	}

	public static void writeLessthanTwoHourWorkDaysData(Map<String, List<java.time.LocalDate>> data,
			String filePathName) {
		try (FileWriter fileWriter = new FileWriter(filePathName)) {
			fileWriter.write("Employee ID, Dates with <2 working hours\n");

			for (Map.Entry<String, List<java.time.LocalDate>> mapEntry : data.entrySet()) {
				String allCombinedDates = mapEntry.getValue().stream().map(date -> date.format(DATE_FORMAT))
						.collect(Collectors.joining("; "));

				fileWriter.write(mapEntry.getKey() + "," + allCombinedDates + "\n");
			}
		} catch (IOException IOE) {
			System.err.println("Error occured while writing <2 hour days: " + IOE.getMessage());
		}
	}

	public static void writePercentTimeSpentTaskCategoryWisePerEmployee(Map<String, Map<String, Double>> data,
			String filePathName) {
		try (FileWriter fileWriter = new FileWriter(filePathName)) {
			fileWriter.write("Employee ID, Task Category, % Time Spent\n");
			for (Map.Entry<String, Map<String, Double>> employeeEntry : data.entrySet()) {
				String employeeID = employeeEntry.getKey();
				Map<String, Double> taskToPercentMap = employeeEntry.getValue();
				for (Map.Entry<String, Double> taskEntry : taskToPercentMap.entrySet()) {
					fileWriter.write(employeeID + "," + taskEntry.getKey() + "," + taskEntry.getValue() + "\n");
				}
			}
		} catch (IOException IOE) {
			System.err.println("Error occurred while writing Task Category wise Percentage data: " + IOE.getMessage());
		}
	}
	
	public static void writeAverageWeeklyHoursGroupByEmployeeAndMonth(Map<String, Map<YearMonth, Double>> data, String filePathName) {
		try(FileWriter fileWriter = new FileWriter(filePathName)){
			fileWriter.write("Employee ID, Month, Weekly working hours average\n");
			for(Map.Entry<String, Map<YearMonth, Double>> employeeEntry : data.entrySet()) {
				String employeeID = employeeEntry.getKey();
				Map<YearMonth, Double> averageWeeklyHoursMap = employeeEntry.getValue();
				
				for(Map.Entry<YearMonth, Double> monthlyHoursEntry: averageWeeklyHoursMap.entrySet()) {
					fileWriter.write(employeeID + "," + monthlyHoursEntry.getKey() + "," + monthlyHoursEntry.getValue() + "\n");
				}
			}
		}
		catch (IOException IOE) {
			System.err.println("Error occurred while writing Monthly weekly hours average per employee data: " + IOE.getMessage());
		}
		
	}

}
