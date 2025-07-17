package service;

import model.EmployeeWorkLog;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnalyticsService {

    public List<EmployeeWorkLog> filterUrgentOrCritical(List<EmployeeWorkLog> logs) {
        return logs.stream()
                .filter(log -> log.getRemarks().toLowerCase().contains("urgent")
                        || log.getRemarks().toLowerCase().contains("critical"))
                .sorted(Comparator.comparing(EmployeeWorkLog::getName))
                .collect(Collectors.toList());
    }

    public Map<String, Double> dailyAvgLast30Days(List<EmployeeWorkLog> logs) {
        LocalDate cutoff = LocalDate.now().minusDays(30);
        return logs.stream()
                .filter(log -> log.getDate().isAfter(cutoff))
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                        Collectors.averagingDouble(EmployeeWorkLog::getHoursWorked)));
    }

    public Map<String, Double> consistencyByCategory(List<EmployeeWorkLog> logs) {
        return logs.stream().collect(Collectors.groupingBy(EmployeeWorkLog::getTaskCategory,
                Collectors.collectingAndThen(Collectors.mapping(EmployeeWorkLog::getHoursWorked, Collectors.toList()),
                        hours -> {
                            double avg = hours.stream().mapToDouble(h -> h).average().orElse(0);
                            return Math.sqrt(hours.stream().mapToDouble(h -> Math.pow(h - avg, 2)).average().orElse(0));
                        })));
    }

    public Map<String, Double> differenceBetweenTwoSheets(List<EmployeeWorkLog> list1, List<EmployeeWorkLog> list2) {
        Map<String, Double> map1 = list1.stream().collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)));

        Map<String, Double> map2 = list2.stream().collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)));

        Set<String> allIds = new HashSet<>(map1.keySet());
        allIds.addAll(map2.keySet());

        Map<String, Double> diff = new HashMap<>();
        for (String id : allIds) {
            double h1 = map1.getOrDefault(id, 0.0);
            double h2 = map2.getOrDefault(id, 0.0);
            diff.put(id, h2 - h1);
        }
        return diff;
    }
    public Map<String, String> timeOfDayClassification(List<EmployeeWorkLog> logs) {
        return logs.stream().collect(Collectors.toMap(
            log -> log.getEmployeeId() + "_" + log.getDate(),
            log -> {
                double hours = log.getHoursWorked();

                if (hours <= 5)
                    return "Morning";
                else if (hours <= 8)
                    return "Afternoon";
                else
                    return "Evening";
            },
            (a, b) -> a  
        ));
    }

  }
