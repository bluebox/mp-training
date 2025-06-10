package EmployeesAnalysisConsoleApp.src;
import java.util.Map;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		String sourceDataPath = "EmployeesAnalysisConsoleApp/SourceDataFiles/Sample_Employee_WorkLogs.csv";
		String resultDataPath = "EmployeesAnalysisConsoleApp/ResultDataFiles/";

		List<EmployeesWorkLogPOJO> employeeLogs = ExcelFileReader.readingEmployeesWorkLogs(sourceDataPath);

		if (employeeLogs == null || employeeLogs.isEmpty()) {
			System.err.println("No employee log data was found. Please check the source data file.");
			return;
		}
		Map<String, List<EmployeesWorkLogPOJO>> topThreeTasks = AnalysingExcelData
				.getTopTasksPerDepartment(employeeLogs);
		Map<String, Double> dailyAverageWorkingHours = AnalysingExcelData
				.getDailyAverageWorkingHours30DaysPerEmployee(employeeLogs);
		double weekendHours = AnalysingExcelData.computeWeekendWorkHours(employeeLogs);
		Map<String, List<LocalDate>> lessThan2Hours = AnalysingExcelData.getLessthanTwoHourWorkDaysData(employeeLogs);
		Map<String, Map<String, Double>> PercentTimeSpentTaskCategoryWisePerEmployeeData = AnalysingExcelData
				.getPercentTimeSpentTaskCategoryWisePerEmployee(employeeLogs);
		Map<String, Map<YearMonth, Double>> averageWeeklyHoursGroupByEmployeeAndMonthData = AnalysingExcelData.getAverageWeeklyHoursGroupByEmployeeAndMonth(employeeLogs);
		

		ExcelFileWriter.writeTopThreeTasks(topThreeTasks, resultDataPath + "Top3TasksPerDepartment.csv");
		ExcelFileWriter.writeWeekendSummary(weekendHours, resultDataPath + "WeekendWorkingHoursSummary.csv");
		ExcelFileWriter.writeDailyAverage(dailyAverageWorkingHours, resultDataPath + "DailyAverageHoursLast30Days.csv");
		ExcelFileWriter.writeLessthanTwoHourWorkDaysData(lessThan2Hours, resultDataPath + "LessThan2Hours.csv");
		ExcelFileWriter.writePercentTimeSpentTaskCategoryWisePerEmployee(
				PercentTimeSpentTaskCategoryWisePerEmployeeData,
				resultDataPath + "PercentTimeSpentTaskCategoryWisePerEmployeeData.csv");
		ExcelFileWriter.writeAverageWeeklyHoursGroupByEmployeeAndMonth(averageWeeklyHoursGroupByEmployeeAndMonthData, resultDataPath + "averageWeeklyHoursGroupByEmployeeAndMonthData.csv");

		System.out.println(
				"Given Employee Logs data has been analysed and all 5 query results have been generated successfully!");
	}
}
