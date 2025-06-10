package com.medplus.casestudies.employeeproject;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeAnalyzer {

    public void calculateWeeklyAverages(List<EmployeeWorkLog> logs) {
        Map<String, Double> weeklyAvg = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Map<Integer, Double> weekHours = list.stream()
                        .collect(Collectors.groupingBy(
                            log -> log.getDate().get(WeekFields.ISO.weekOfWeekBasedYear()),
                            Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)));
                    return weekHours.values().stream().mapToDouble(Double::doubleValue).average().orElse(0);
                })));

        new ExcelWriter().writeWeeklyAverages(weeklyAvg, "src/main/java/com/medplus/casestudies/employeeproject/Weekly_Averages_Output.xlsx");
    }

    public void calculateDailyTrendLast30Days(List<EmployeeWorkLog> logs) {
        LocalDate maxDate = logs.stream().map(EmployeeWorkLog::getDate).max(LocalDate::compareTo).orElse(LocalDate.now());
        LocalDate startDate = maxDate.minusDays(45);

        Map<String, Map<LocalDate, Double>> trend = logs.stream()
            .filter(log -> !log.getDate().isBefore(startDate))
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                Collectors.groupingBy(EmployeeWorkLog::getDate,
                    Collectors.averagingDouble(EmployeeWorkLog::getHoursWorked))));

        new ExcelWriter().writeDailyTrend(trend);
    }

    public void findZeroHourStreaks(List<EmployeeWorkLog> logs) {
        Map<String, List<LocalDate>> zeroStreaks = new HashMap<>();

        logs.stream()
            .filter(log -> log.getHoursWorked() == 0)
            .sorted(Comparator.comparing(EmployeeWorkLog::getEmployeeId).thenComparing(EmployeeWorkLog::getDate))
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId))
            .forEach((empId, empLogs) -> {
                List<LocalDate> streak = new ArrayList<>();
                for (int i = 0; i < empLogs.size(); i++) {
                    LocalDate current = empLogs.get(i).getDate();
                    if (!streak.isEmpty() && current.minusDays(1).equals(streak.get(streak.size() - 1))) {
                        streak.add(current);
                    } else {
                        if (streak.size() >= 3) zeroStreaks.put(empId, new ArrayList<>(streak));
                        streak.clear();
                        streak.add(current);
                    }
                }
                if (streak.size() >= 3) zeroStreaks.put(empId, streak);
            });

        new ExcelWriter().writeZeroHourStreaks(zeroStreaks);
    }

    public void findLessThanTwoHoursDays(List<EmployeeWorkLog> logs) {
        Map<String, Map<LocalDate, Double>> result = logs.stream()
            .filter(log -> log.getHoursWorked() < 2)
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                Collectors.groupingBy(EmployeeWorkLog::getDate,
                    Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))));

        new ExcelWriter().writeLowHourDays(result);
    }

    public void findProjectHoppers(List<EmployeeWorkLog> logs) {
        Map<String, Map<String, Long>> result = logs.stream()
            .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                Collectors.groupingBy(log -> log.getDate().getYear() + "-" + log.getDate().getMonthValue(),
                    Collectors.collectingAndThen(
                        Collectors.mapping(EmployeeWorkLog::getProjectId, Collectors.toSet()),
                        set -> (long) set.size()))))
            .entrySet().stream()
            .filter(e -> e.getValue().values().stream().anyMatch(count -> count > 1))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue));

        new ExcelWriter().writeProjectHoppers(result);
    }
}
