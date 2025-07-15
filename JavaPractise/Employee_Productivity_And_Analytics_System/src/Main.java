import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String excelPath = ".//DataFiles//EmployeeWorkLogs_Consistent.xlsx";
        List<EmployeeWorkLog> employeeList = ExcelReader.readLogs(excelPath);

        
        
        // Task 7: Employees logging >10 hrs per day
        var over10 = AnalyticsProcessor.logsOver10Hours(employeeList);
        System.out.println("Task 7: Employees logging >10 hours per day");
        AnalyticsProcessor.exportToCSV("Over10Hours.csv",
                over10.entrySet().stream()
                        .map(e -> e.getKey() + " -> " + e.getValue().size() + " days")
                        .toList());
 
        
        
        
        // Task 12: Project-wise total and average hours
        System.out.println("Task 12: Project-wise productivity (per employee)");
        Map<String, Map<String, DoubleSummaryStatistics>> result = AnalyticsProcessor.projectProductivity(employeeList);
        List<String> lines = new ArrayList<>();
        lines.add("ProjectID,EmployeeID,TotalHours,AverageHours,Entries");
        for (Map.Entry<String, Map<String, DoubleSummaryStatistics>> projectEntry : result.entrySet()) {
            String projectId = projectEntry.getKey();
            Map<String, DoubleSummaryStatistics> empStats = projectEntry.getValue();
            for (Map.Entry<String, DoubleSummaryStatistics> empEntry : empStats.entrySet()) {
                String empId = empEntry.getKey();
                DoubleSummaryStatistics stats = empEntry.getValue();
                lines.add(String.format("%s,%s,%.2f,%.2f,%d",
                        projectId, empId, stats.getSum(), stats.getAverage(), stats.getCount()));
            }
        }
        AnalyticsProcessor.exportToCSV("ProjectProductivity.csv", lines);
        
        
        // Task 20: Drop >40% from previous month
        var drops = AnalyticsProcessor.detectDrop(employeeList);
        System.out.println("Task 20: >40% Drop in monthly hours");
        AnalyticsProcessor.exportToCSV("Drop40Percent.csv", drops);
        
        
        // Task 24: Top 2 employees per department by total hours
        var top2 = AnalyticsProcessor.top2ByDepartment(employeeList);
        System.out.println("Task 24: Top 2 employees per department");
        
        AnalyticsProcessor.exportToCSV("Top2ByDepartment.csv",
                top2.entrySet().stream()
                        .map(e -> e.getKey() + " -> " + String.join(", ", e.getValue()))
                        .toList());

        
        
        
        // Task 25: Sort logs by Department > Project > Date
        var sortedemployeeList = AnalyticsProcessor.sortLogs(employeeList);
        System.out.println("Task 25: Sorted logs (Dept > Project > Date)");
        AnalyticsProcessor.exportToCSV("SortedLogs.csv",
                sortedemployeeList.stream()
                        .map(l -> String.join(",", l.getDepartment(), l.getProjectId(), l.getDate().toString(),
                                l.getEmployeeId(), l.getName(), l.getTaskCategory(),
                                String.valueOf(l.getHoursWorked())))
                        .toList());
    }
}
