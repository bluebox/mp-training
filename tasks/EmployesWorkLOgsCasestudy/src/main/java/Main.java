
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            WorkLogAnalyzer analyzer = new WorkLogAnalyzer("/home/mphs/Desktop/Sample_Employee_WorkLogs.xlsx");

            // Task 9: Top 5 employees with highest hours in last 60 days
            Map<String, Double> top5 = analyzer.getTop5EmployeesLast60Days();
            analyzer.exportToCSV(top5, "top5_employees.csv");

            // Task 18: Find employees with 3+ zero-hour consecutive days
            Set<String> zeroDayEmployees = analyzer.getEmployeesWith3ConsecutiveZeroDays();
            Map<String, String> zeroDayMap = zeroDayEmployees.stream()
                    .collect(Collectors.toMap(e -> e, e -> "3+ consecutive zero days"));
            analyzer.exportToCSV(zeroDayMap, "zero_day_employees.csv");

            // Task 4: Group by employee and month; compute average weekly hours
            Map<String, Map<YearMonth, Double>> avgWeekly = analyzer.getAverageWeeklyHoursPerEmployeePerMonth();
            analyzer.exportComplexToCSV(avgWeekly, "avg_weekly_hours.csv");

            System.out.println("All reports generated successfully!");
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }
}