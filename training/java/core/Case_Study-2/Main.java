package study;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<EmployeeWorkLog> logs1 = ExcelReader.readExcel("worklog1.xlsx");
        List<EmployeeWorkLog> logs2 = ExcelReader.readExcel("worklog2.xlsx");

        // Task 2
        List<String[]> task2 = AnalyticsEngine.top3TasksPerDepartment(logs1);
        CSVWriterUtil.writeToCSV(task2, "Task2_TopTasksPerDepartment.csv");

        // Task 6
        List<String[]> task6 = AnalyticsEngine.departmentProjectTotalTime(logs1);
        CSVWriterUtil.writeToCSV(task6, "Task6_DeptProjectHours.csv");


        // Task 13
        List<String[]> task13 = AnalyticsEngine.dailyAvgTrendLast30Days(logs1);
        CSVWriterUtil.writeToCSV(task13, "Task13_DailyAvgLast30Days.csv");


        
        // Task 22
        List<String[]> task22 = AnalyticsEngine.computeHourDifference(logs1, logs2);
        CSVWriterUtil.writeToCSV(task22, "Task22_HourDifferenceBetweenSheets.csv");


        // Task 30
        List<String[]> task30 = AnalyticsEngine.meetingTimeAnalysis(logs1);
        CSVWriterUtil.writeToCSV(task30, "Task30_MeetingTime.csv");
    }
}
