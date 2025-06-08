import java.util.Map;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String sourceDataPath = "EmployeesAnalysisConsoleApp/SourceDataFiles/Sample_Employee_WorkLogs.csv";
        String resultDataPath = "EmployeesAnalysisConsoleApp/ResultDataFiles/";

        List<EmployeesWorkLogPOJO> employeeLogs = ExcelFileReader.readingEmployeesWorkLogs(sourceDataPath);

        if (employeeLogs == null || employeeLogs.isEmpty()) 
        {
            System.err.println("No employee log data was found. Please check the source data file.");
            return;
        }
        Map<String, List<EmployeesWorkLogPOJO>> topThreeTasks = AnalysingExcelData.getTopTasksPerDepartment(employeeLogs);
        Map<String, Double> dailyAverageWorkingHours = AnalysingExcelData.getDailyAverageWorkingHours30DaysPerEmployee(employeeLogs);
        double weekendHours = AnalysingExcelData.computeWeekendWorkHours(employeeLogs);
        Map<String, List<java.time.LocalDate>> lessThan2Hours = AnalysingExcelData.getLessthanTwoHourWorkDaysData(employeeLogs);

        ExcelFileWriter.writeTopThreeTasks(topThreeTasks, resultDataPath + "Top3TasksPerDepartment.csv");
        ExcelFileWriter.writeWeekendSummary(weekendHours, resultDataPath + "WeekendWorkingHoursSummary.csv");
        ExcelFileWriter.writeDailyAverage(dailyAverageWorkingHours, resultDataPath + "DailyAverageHoursLast30Days.csv");
        ExcelFileWriter.writeLessthanTwoHourWorkDaysData(lessThan2Hours, resultDataPath + "LessThan2Hours.csv");

        System.out.println("Given Employee Logs data has been analysed and all 5 query results have been generated successfully!");
    }
}
