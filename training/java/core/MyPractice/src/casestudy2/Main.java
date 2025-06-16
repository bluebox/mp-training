package casestudy2;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        List<EmployeeWorkLog> logs = CSVReader.readCSV("casestudy2/Sample_Employee_WorkLogs.csv");

        CSVWriter writer1 = new CSVWriter("task1_output.csv");
        writer1.writeSectionTitle("Task 1: Engineering employees with >2 projects");
        writer1.writeLines(Task1.getEngineersWithMultipleProjects(logs));
        writer1.close();
        System.out.println("Outputs has been written to task1_output.csv");

        CSVWriter writer9 = new CSVWriter("task9_output.csv");
        writer9.writeSectionTitle("Task 9: Top 5 employees with highest hours in last 60 days");
        writer9.writeLines(Task9.getEmployeesHighestWorkingHours(logs));
        writer9.close();
        System.out.println("Outputs has been written to task9_output.csv");

        CSVWriter writer10 = new CSVWriter("task10_output.csv");
        writer10.writeSectionTitle("Task 10: 'Bug Fix' tasks grouped by category and day");
        writer10.writeLines(Task10.getBugFixStatsByCategoryAndDay(logs));
        writer10.close();
        System.out.println("Outputs has been written to task10_output.csv");

        CSVWriter writer12 = new CSVWriter("task12_output.csv");
        writer12.writeSectionTitle("Task 12: Project-wise productivity");
        writer12.writeLines(Task12.generateProjectHoursReport(logs));
        writer12.close();
        System.out.println("Outputs has been written to task12_output.csv");

        CSVWriter writer25 = new CSVWriter("task25_output.csv");
        writer25.writeSectionTitle("Task 25: Sorted by Department > Project > Date");
        writer25.writeLines(Task25.exportWorkLogsAsCSV(logs));
        writer25.close();
        System.out.println("Outputs has been written to task25_output.csv");
    }
}

class Task1 {
    public static List<String> getEngineersWithMultipleProjects(List<EmployeeWorkLog> logs) {
        Stream<EmployeeWorkLog> engineeringLogs = logs.stream()
            .filter(log -> log.getDepartment().toLowerCase().contains("engineer"));
        Map<String, Set<String>> employeeToProjects = engineeringLogs
            .collect(Collectors.groupingBy(
                log -> log.getEmployeeId(),  
                Collectors.mapping(
                    log -> log.getProjectId(),  
                    Collectors.toSet()  
                )
            ));
        Stream<Map.Entry<String, Set<String>>> filteredEntries = employeeToProjects.entrySet().stream()
            .filter(entry -> entry.getValue().size() > 2);
        
       //format the results from map to string to display
        List<String> result = filteredEntries
            .map(entry -> entry.getKey() + "," + entry.getValue().size() + " unique projects")
            .collect(Collectors.toList());
        
        return result;
    }
}

class Task9 {
    public static List<String> getEmployeesHighestWorkingHours(List<EmployeeWorkLog> logs) {
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
    public static List<String> getBugFixStatsByCategoryAndDay(List<EmployeeWorkLog> logs) {
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
    public static List<String> generateProjectHoursReport(List<EmployeeWorkLog> logs) {
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
	//Export sorted work logs as CSV data.
    public static List<String> exportWorkLogsAsCSV(List<EmployeeWorkLog> logs) {
        logs.sort(Comparator
                .comparing(EmployeeWorkLog::getDepartment)
                .thenComparing(EmployeeWorkLog::getProjectId)
                .thenComparing(EmployeeWorkLog::getDate));
        List<String> result = new ArrayList<>();
        for (EmployeeWorkLog log : logs) {
            String formatted = String.join(",",
                    log.getDepartment(),
                    log.getProjectId(),
                    log.getDate().toString(),
                    log.getEmployeeId(),
                    log.getName(),
                    String.valueOf(log.getHoursWorked()));
            result.add(formatted);
        }
        return result;

    }
}
