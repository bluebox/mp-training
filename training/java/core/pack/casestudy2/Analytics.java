package casestudy2;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Analytics {

    public static void performAnalytics(List<EmployeeWorkLog> logs) {
        if (logs == null || logs.isEmpty()) {
            System.out.println("No work logs available to analyze.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("analyticsReport.csv"))) {
            extractAndCountTags(logs, writer);
            writer.println(); // gap between outputs
            generateEmployeeSummaryStats(logs, writer);
            writer.println();
            displayCategoryPercentagesPerProject(logs, writer);
            writer.println();
            displayTop3TasksPerDepartment(logs, writer);
            writer.println();
            displayUrgentOrCriticalRemarks(logs, writer);
        } catch (Exception e) {
            System.err.println("Error writing analyticsReport.csv: " + e.getMessage());
        }
    }

    // 1. Extract tags starting with '#' and count their occurrences
    private static void extractAndCountTags(List<EmployeeWorkLog> logs, PrintWriter writer) {
        System.out.println("-------------------------");
        System.out.println("Tags Count:");
        writer.println("Tags Count:");

//        Map<String, Long> tagCounts = logs.stream()
//            .flatMap(log -> Arrays.stream(log.getRemarks().split("\\s+")))
//            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        Map<String, Long> tagCounts = logs.stream()
                .map(log -> log.getRemarks())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        if (tagCounts.isEmpty()) {
            System.out.println("  No tags found.");
            writer.println("No tags found.");
        } else {
            writer.println("Tag,Count");
            tagCounts.forEach((tag, count) -> {
                System.out.printf("  %s: %d%n", tag, count);
                writer.printf("%s,%d%n", tag, count);
            });
        }
    }

    // 2. Calculate min, max, average hours per employee
    private static void generateEmployeeSummaryStats(List<EmployeeWorkLog> logs, PrintWriter writer) {
        System.out.println("-------------------------");
        System.out.println("Employee Work Summary:");
        writer.println("Employee Work Summary:");
        writer.println("EmployeeId,Min Hours,Max Hours,Average Hours");

        Map<String, DoubleSummaryStatistics> statsMap = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                    Collectors.summarizingDouble(EmployeeWorkLog::getHoursWorked)));

        statsMap.forEach((empId, stats) -> {
            System.out.printf("  %s -> Min: %.2f, Max: %.2f, Avg: %.2f%n",
                    empId, stats.getMin(), stats.getMax(), stats.getAverage());
            writer.printf("%s,%.2f,%.2f,%.2f%n", empId, stats.getMin(), stats.getMax(), stats.getAverage());
        });
    }

    // 3. Calculate category % contribution per project
    private static void displayCategoryPercentagesPerProject(List<EmployeeWorkLog> logs, PrintWriter writer) {
        System.out.println("-------------------------");
        System.out.println("Category % Contribution per Project:");
        writer.println("Category % Contribution per Project:");
        writer.println("Project,Category,Percentage");

        //Map<projectId,Map<Category,hoursworked>>
        Map<String, Map<String, Double>> projectCategoryMap = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId,
                    Collectors.groupingBy(EmployeeWorkLog::getTaskCategory,
                            Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))));

        for (Map.Entry<String, Map<String, Double>> entry : projectCategoryMap.entrySet()) {
            String project = entry.getKey();
            Map<String, Double> categoryMap = entry.getValue();
            double total = categoryMap.values().stream().mapToDouble(Double::doubleValue).sum();

            System.out.println("Project: " + project);
            for (var categoryEntry : categoryMap.entrySet()) {
                double percent = (categoryEntry.getValue() / total) * 100;
                System.out.printf("  %s: %.2f%%%n", categoryEntry.getKey(), percent);
                writer.printf("%s,%s,%.2f%n", project, categoryEntry.getKey(), percent);
            }
        }
    }

    // 4. Get top 3 most time-consuming tasks per department
    private static void displayTop3TasksPerDepartment(List<EmployeeWorkLog> logs, PrintWriter writer) {
        System.out.println("-------------------------");
        System.out.println("Top 3 Tasks per Department:");
        writer.println("Top 3 Tasks per Department:");
        writer.println("Department,Date,Category,Hours");

       // Map<department,list of top3 rows that have highest task>
        Map<String, List<EmployeeWorkLog>> topTasks = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getDepartment,
                    Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
                            .sorted(Comparator.comparingDouble(EmployeeWorkLog::getHoursWorked).reversed())
                            .limit(3)
                            .collect(Collectors.toList()))));

        topTasks.forEach((dept, taskList) -> {
            System.out.println("Department: " + dept);
            taskList.forEach(task -> {
                System.out.printf("  [%s] %s - %.2f hours%n", task.getDate(), task.getTaskCategory(), task.getHoursWorked());
                writer.printf("%s,%s,%s,%.2f%n", dept, task.getDate(), task.getTaskCategory(), task.getHoursWorked());
            });
        });
    }

    // 5. Filter logs with "urgent"/"critical" in remarks and sort by employee name
    private static void displayUrgentOrCriticalRemarks(List<EmployeeWorkLog> logs, PrintWriter writer) {
        System.out.println("-------------------------");
        System.out.println("Urgent / Critical Remarks:");
        writer.println("Urgent / Critical Remarks:");
        writer.println("Name,Date,Remarks");

        List<EmployeeWorkLog> filtered = logs.stream()
            .filter(log -> {
                String remarks = log.getRemarks().toLowerCase();
                return remarks.contains("urgent") || remarks.contains("critical");
            })
            .sorted(Comparator.comparing(EmployeeWorkLog::getName))
            .toList();

        if (filtered.isEmpty()) {
            System.out.println("  No urgent or critical entries found.");
            writer.println("No urgent or critical entries found.");
        } else {
            filtered.forEach(log -> {
                System.out.printf("  %s (%s): %s%n", log.getName(), log.getDate(), log.getRemarks());
                writer.printf("%s,%s,%s%n", log.getName(), log.getDate(), log.getRemarks());
            });
        }
    }
}
