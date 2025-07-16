package EmpProductivity;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

public class AnalyticsProcessor {

    // 1. Average Weekly Hours Per Employee Per Month
    public static List<String[]> averageWeeklyHours(List<EmployeeWorkLog> logs) {
        WeekFields weekFields = WeekFields.ISO;

        Map<String, Map<Integer, List<EmployeeWorkLog>>> grouped = logs.stream()
            .collect(Collectors.groupingBy(
                EmployeeWorkLog::getEmployeeId,
                Collectors.groupingBy(log -> log.getDate().getMonthValue())
            ));

        List<String[]> result = new ArrayList<>();
        result.add(new String[]{"Employee ID", "Month", "Average Weekly Hours"});

        grouped.forEach((empId, monthlyLogs) -> {
            monthlyLogs.forEach((month, logList) -> {
                long weeks = logList.stream()
                    .map(log -> log.getDate().get(weekFields.weekOfWeekBasedYear()))
                    .distinct()
                    .count();

                double totalHours = logList.stream()
                    .mapToDouble(EmployeeWorkLog::getHoursWorked)
                    .sum();

                double avg = weeks == 0 ? 0 : totalHours / weeks;

                result.add(new String[]{empId, String.valueOf(month), String.format("%.2f", avg)});
            });
        });

        return result;
    }

    // 2. Employees Working in 3 or more than 3 category's
    public static List<String[]> multiCategoryEmployees(List<EmployeeWorkLog> logs) {
        WeekFields weekFields = WeekFields.ISO;

        Map<String, Map<Integer, Map<Integer, Set<String>>>> grouped = logs.stream()
            .collect(Collectors.groupingBy(
                EmployeeWorkLog::getEmployeeId,
                Collectors.groupingBy(log -> log.getDate().getYear(),
                    Collectors.groupingBy(log -> log.getDate().get(weekFields.weekOfWeekBasedYear()),
                        Collectors.mapping(EmployeeWorkLog::getTaskCategory, Collectors.toSet())
                    )
                )
            ));

        List<String[]> result = new ArrayList<>();
        result.add(new String[]{"Employee ID", "Year", "Week", "Distinct Categories"});

        grouped.forEach((empId, yearMap) -> {
            yearMap.forEach((year, weekMap) -> {
                weekMap.forEach((week, categories) -> {
                    if (categories.size() >= 3) {
                        result.add(new String[]{empId, String.valueOf(year), String.valueOf(week), String.valueOf(categories.size())});
                    }
                });
            });
        });

        return result;
    }

    // 3. Overtime employee working More than 9 hours per day
    public static List<String[]> overtimeLogs(List<EmployeeWorkLog> logs) {
        Map<String, Map<LocalDate, Double>> dailyTotals = logs.stream()
            .collect(Collectors.groupingBy(
                EmployeeWorkLog::getEmployeeId,
                Collectors.groupingBy(EmployeeWorkLog::getDate,
                    Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))
            ));

        List<String[]> result = new ArrayList<>();
        result.add(new String[]{"Employee ID", "Date", "Hours Worked"});

        dailyTotals.forEach((empId, dateMap) -> {
            dateMap.forEach((date, hours) -> {
                if (hours > 9.0) {
                    result.add(new String[]{empId, date.toString(), String.format("%.2f", hours)});
                }
            });
        });

        return result;
    }

    // 4. Critical Projects if More than 5 employees work on a single project for at least 200 hours
    public static List<String[]> criticalProjects(List<EmployeeWorkLog> logs) {
        Map<String, List<EmployeeWorkLog>> projectLogs = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId));

        List<String[]> result = new ArrayList<>();
        result.add(new String[]{"Project ID", "Employee Count", "Total Hours"});

        projectLogs.forEach((projectId, logList) -> {
            Set<String> employeeIds = logList.stream()
                .map(EmployeeWorkLog::getEmployeeId)
                .collect(Collectors.toSet());

            double totalHours = logList.stream()
                .mapToDouble(EmployeeWorkLog::getHoursWorked)
                .sum();

            if (employeeIds.size() > 5 && totalHours >= 200) {
                result.add(new String[]{projectId, String.valueOf(employeeIds.size()), String.format("%.2f", totalHours)});
            }
        });

        return result;
    }

    // 5. Category Contribution Per Project
    public static List<String[]> categoryContributionPerProject(List<EmployeeWorkLog> logs) {
        Map<String, List<EmployeeWorkLog>> projectMap = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId));

        List<String[]> result = new ArrayList<>();
        result.add(new String[]{"Project ID", "Category", "Percentage Contribution"});

        projectMap.forEach((projectId, logList) -> {
            double totalHours = logList.stream()
                .mapToDouble(EmployeeWorkLog::getHoursWorked)
                .sum();

            Map<String, Double> categoryHours = logList.stream()
                .collect(Collectors.groupingBy(EmployeeWorkLog::getTaskCategory,
                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)));

            categoryHours.forEach((category, hours) -> {
                double percent = (hours / totalHours) * 100;
                result.add(new String[]{projectId, category, String.format("%.2f", percent)});
            });
        });

        return result;
    }
}