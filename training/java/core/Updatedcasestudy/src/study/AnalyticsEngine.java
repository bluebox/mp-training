package study;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;
public class AnalyticsEngine {
	
	

    // Task 2
    public static List<String[]> top3TasksPerDepartment(List<EmployeeWorkLog> logs) {
        List<String[]> result = new ArrayList<>();
        result.add(new String[]{"Department", "Task Category", "Total Hours"});

        Map<String, Map<String, Double>> taskHours = logs.stream()
                .collect(Collectors.groupingBy(EmployeeWorkLog::getDepartment,
                        Collectors.groupingBy(EmployeeWorkLog::getTaskCategory,
                                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))));

        for (var deptEntry : taskHours.entrySet()) {
            String department = deptEntry.getKey();
            
         
            deptEntry.getValue().entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .limit(3)
                    .forEach(e -> result.add(new String[]{department, e.getKey(), String.valueOf(e.getValue())}));
        }

        return result;
    }

    // Task 6
    public static List<String[]> departmentProjectTotalTime(List<EmployeeWorkLog> logs) {
        return logs.stream()
                .collect(Collectors.groupingBy(l -> l.getDepartment() + " - " + l.getProjectId(),
                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(e -> new String[]{e.getKey(), String.format("%.2f", e.getValue())})
                .collect(Collectors.toCollection(() -> {
                    List<String[]> header = new ArrayList<>();
                    header.add(new String[]{"Department - Project", "Total Hours"});
                    return header;
                }));
    }

    //  Task 13
    public static List<String[]> dailyAvgTrendLast30Days(List<EmployeeWorkLog> logs) {
        List<LocalDate> last30Days = logs.stream()
                .map(EmployeeWorkLog::getDate)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(30)
                .collect(Collectors.toList());

        return logs.stream()
                .filter(l -> last30Days.contains(l.getDate()))
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId))
                .entrySet().stream()
                .map(e -> new String[]{
                        e.getKey(),
                        String.format("%.2f", e.getValue().stream().mapToDouble(EmployeeWorkLog::getHoursWorked).average().orElse(0.0))
                })
                .collect(Collectors.toCollection(() -> {
                    List<String[]> header = new ArrayList<>();
                    header.add(new String[]{"Employee ID", "Daily Average Hours (Last 30 Days)"});
                    return header;
                }));
    }

    //  Task 22
    public static List<String[]> computeHourDifference(List<EmployeeWorkLog> logs1, List<EmployeeWorkLog> logs2) {
        Map<String, Double> map1 = logs1.stream()
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)));

        Map<String, Double> map2 = logs2.stream()
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)));

        Set<String> allIds = new HashSet<>();
        allIds.addAll(map1.keySet());
        allIds.addAll(map2.keySet());

        List<String[]> output = new ArrayList<>();
        output.add(new String[]{"Employee ID", "Hours in Sheet 1", "Hours in Sheet 2", "Difference"});

        for (String id : allIds) {
            double h1 = map1.getOrDefault(id, 0.0);
            double h2 = map2.getOrDefault(id, 0.0);
            output.add(new String[]{id, String.valueOf(h1), String.valueOf(h2), String.format("%.2f", h1 - h2)});
        }

        return output;
    }

   
    public static List<String[]> meetingTimeAnalysis(List<EmployeeWorkLog> logs) {
        List<String[]> output = new ArrayList<>();
        output.add(new String[]{"Employee ID", "Meeting Hours"});
        
        logs.stream()
                .filter(l -> l.getRemarks().toLowerCase().contains("meeting"))
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)))
                .forEach((id, hrs) -> output.add(new String[]{id, String.format("%.2f", hrs)}));

        return output;
    }
}
