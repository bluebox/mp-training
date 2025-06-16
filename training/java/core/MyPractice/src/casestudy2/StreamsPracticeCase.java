package casestudy2;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
public class StreamsPracticeCase {
    public static void main(String[] args) throws Exception {

        List<EmployeeWorkLog> list = new ArrayList<>();

        list.add(new EmployeeWorkLog("E101", "Alice", "Engineering", "P1", LocalDate.of(2024, 3, 1), "Development", 6.0,
                "initial commit"));
        list.add(new EmployeeWorkLog("E101", "Alice", "Engineering", "P2", LocalDate.of(2024, 3, 3), "Bug Fix", 5.0,
                "urgent issue"));
        list.add(new EmployeeWorkLog("E101", "Alice", "Engineering", "P3", LocalDate.of(2024, 3, 5), "Documentation",
                3.0, ""));
        list.add(new EmployeeWorkLog("E102", "Bob", "QA", "P1", LocalDate.of(2024, 3, 1), "Testing", 4.0, ""));
        list.add(new EmployeeWorkLog("E103", "Charlie", "Engineering", "P1", LocalDate.of(2024, 3, 2), "Development",
                8.0, ""));
        list.add(new EmployeeWorkLog("E103", "Charlie", "Engineering", "P2", LocalDate.of(2024, 3, 3), "Bug Fix", 6.0,
                ""));
        list.add(new EmployeeWorkLog("E104", "David", "HR", "P1", LocalDate.of(2024, 3, 2), "Meeting", 2.0,
                "monthly meeting"));
        Task1.fun(list);
        // CSVWriter writer1 = new CSVWriter("casestudy2/task1_output.csv");
        // writer1.writeSectionTitle("Task 1: Engineering employees with >2 projects");
        // writer1.writeLines(Task1.getEngineersWithMoreThanTwoProjects(logs));
        // writer1.close();
        // System.out.println("Outputs has been written to task1_output.csv");

        // CSVWriter writer9 = new CSVWriter("casestudy2/task9_output.csv");
        // writer9.writeSectionTitle("Task 9: Top 5 employees with highest hours in last 60 days");
        // writer9.writeLines(Task9.getTop5EmployeesInLast60Days(logs));
        // writer9.close();
        // System.out.println("Outputs has been written to task9_output.csv");

        // CSVWriter writer10 = new CSVWriter("casestudy2/task10_output.csv");
        // writer10.writeSectionTitle("Task 10: 'Bug Fix' tasks grouped by category and day");
        // writer10.writeLines(Task10.groupBugFixTasksByCategoryAndDayOfWeek(logs));
        // writer10.close();
        // System.out.println("Outputs has been written to task10_output.csv");

        // CSVWriter writer12 = new CSVWriter("casestudy2/task12_output.csv");
        // writer12.writeSectionTitle("Task 12: Project-wise productivity");
        // writer12.writeLines(Task12.getProjectWiseEmployeeProductivity(logs));
        // writer12.close();
        // System.out.println("Outputs has been written to task12_output.csv");

        // CSVWriter writer25 = new CSVWriter("casestudy2/task25_output.csv");
        // writer25.writeSectionTitle("Task 25: Sorted by Department > Project > Date");
        // writer25.writeLines(Task25.sortByDepartmentProjectAndDate(logs));
        // writer25.close();
        // System.out.println("Outputs has been written to task25_output.csv");
    }
}

class Task1 {
    public static List<String> getEngineersWithMoreThanTwoProjects(List<EmployeeWorkLog> logs) {
        return logs.stream()
                .filter(log -> log.getDepartment().toLowerCase().contains("engineer"))
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                        Collectors.mapping(EmployeeWorkLog::getProjectId, Collectors.toSet())))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 2)
                .map(e -> e.getKey() + "," + e.getValue().size() + " unique projects")
                .collect(Collectors.toList());
    }

    public static void fun(List<EmployeeWorkLog> logs) {
        List<EmployeeWorkLog> l=logs.stream()
                .filter(log -> log.getDepartment().toLowerCase().contains("engineer"))
                .collect(Collectors.toList());
        System.out.println(l);
    }

}

class Task9 {
    public static List<String> getTop5EmployeesInLast60Days(List<EmployeeWorkLog> logs) {
        LocalDate thresholdDate = LocalDate.now().minusDays(60);
        return logs.stream()
                .filter(log -> log.getDate().isAfter(thresholdDate))
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .map(e -> e.getKey() + "," + e.getValue() + " hrs")
                .collect(Collectors.toList());
    }
}

class Task10 {
    public static List<String> groupBugFixTasksByCategoryAndDayOfWeek(List<EmployeeWorkLog> logs) {
        return logs.stream()
                .filter(log -> log.getTaskCategory().toLowerCase().contains("bug fix"))
                .collect(Collectors.groupingBy(
                        log -> log.getTaskCategory() + "_" + log.getDate().getDayOfWeek(),
                        Collectors.counting()))
                .entrySet().stream()
                .map(e -> e.getKey() + "," + e.getValue() + " tasks")
                .collect(Collectors.toList());
    }
}

class Task12 {
    public static List<String> getProjectWiseEmployeeProductivity(List<EmployeeWorkLog> logs) {
        Map<String, Map<String, List<EmployeeWorkLog>>> grouped = logs.stream()
                .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId,
                        Collectors.groupingBy(EmployeeWorkLog::getEmployeeId)));

        List<String> result = new ArrayList<>();
        grouped.forEach((projectId, empLogs) -> {
            result.add("Project: " + projectId);
            empLogs.forEach((empId, logList) -> {
                double total = logList.stream().mapToDouble(EmployeeWorkLog::getHoursWorked).sum();
                double avg = total / logList.size();
                result.add(empId + "," + total + "," + String.format("%.2f", avg));
            });
            result.add("");
        });
        return result;
    }
}

class Task25 {
    public static List<String> sortByDepartmentProjectAndDate(List<EmployeeWorkLog> logs) {
        return logs.stream()
                .sorted(Comparator.comparing(EmployeeWorkLog::getDepartment)
                        .thenComparing(EmployeeWorkLog::getProjectId)
                        .thenComparing(EmployeeWorkLog::getDate))
                .map(log -> String.join(",",
                        log.getDepartment(), log.getProjectId(), log.getDate().toString(),
                        log.getEmployeeId(), log.getName(), String.valueOf(log.getHoursWorked())))
                .collect(Collectors.toList());
    }
}
   