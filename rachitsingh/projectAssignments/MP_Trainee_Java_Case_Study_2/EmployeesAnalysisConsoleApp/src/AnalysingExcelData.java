import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalysingExcelData {

    // Query-1.  Sort by task category and hours descending, top 3 time-consuming tasks per department.
    public static Map<String, List<EmployeesWorkLogPOJO>> getTopTasksPerDepartment(List<EmployeesWorkLogPOJO> employeeLogs)
    {
        Map<String, List<EmployeesWorkLogPOJO>> departmentToLogsMap = employeeLogs.stream()
                .collect(Collectors.groupingBy(EmployeesWorkLogPOJO::getDepartment));

        Map<String, List<EmployeesWorkLogPOJO>> queryResult = new HashMap<>();

        for (Map.Entry<String, List<EmployeesWorkLogPOJO>> mapEntry : departmentToLogsMap.entrySet()) {
            String department = mapEntry.getKey();
            List<EmployeesWorkLogPOJO> departmentLogs = mapEntry.getValue();

            List<EmployeesWorkLogPOJO> topThreeTasks = departmentLogs.stream()
                    .sorted(Comparator.comparing(EmployeesWorkLogPOJO::getTaskCategory)
                            .thenComparing(Comparator.comparing(EmployeesWorkLogPOJO::getHoursWorked).reversed()))
                    .limit(3)
                    .collect(Collectors.toList());

            queryResult.put(department, topThreeTasks);
        }
        return queryResult;
    }
    
    // Query-2. Weekend logs, summarize weekend hours.
    public static double computeWeekendWorkHours(List<EmployeesWorkLogPOJO> employeeLogs)
    {
        return employeeLogs.stream().filter(
                employeeLog -> {
                    DayOfWeek day = employeeLog.getDate().getDayOfWeek();
                    return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
                })
                .map(EmployeesWorkLogPOJO::getHoursWorked)
                .reduce(0.0, Double::sum);
    }
    
    // Query-3. Daily average hours trend in last 30 working days per employee.
    public static Map<String, Double> getDailyAverageWorkingHours30DaysPerEmployee(List<EmployeesWorkLogPOJO> employeeLogs)
    {
        LocalDate thirtyDaysBefore = LocalDate.now().minusDays(30);

        return employeeLogs.stream()
                .filter(employeeLog -> !employeeLog.getDate().isBefore(thirtyDaysBefore))
                .collect(Collectors.groupingBy(EmployeesWorkLogPOJO::getEmployeeID,
                        Collectors.averagingDouble(EmployeesWorkLogPOJO::getHoursWorked)));

    }
    //Query-4. Days with <2 hours, group by employee and date.
    public static Map<String, List<LocalDate>> getLessthanTwoHourWorkDaysData(List<EmployeesWorkLogPOJO> employeeLogs)
    {
        Map<String, List<LocalDate>> queryResult = new HashMap<>();
        employeeLogs.stream()
                .filter(employeeLog -> employeeLog.getHoursWorked() < 2.0)
                .forEach(employeeLog -> {
                    queryResult.computeIfAbsent(employeeLog.getEmployeeID(), _ -> new ArrayList<>())
                            .add(employeeLog.getDate());
                });
        return queryResult;
    }
    // Query-5  Time period-based grouping (morning, afternoon, evening).
    // => Data insufficient (no time related data is given, only date is given)
}
