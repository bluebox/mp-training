package dev.tulasidhar.case_study_2_excelfilereading.util;

import java.io.FileWriter;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dev.tulasidhar.case_study_2_excelfilereading.model.EmployeeWorkLog;

public class TaskTwo {
    public static void taskTwoProcess(List<EmployeeWorkLog> employees) throws Exception {
        Map<DayOfWeek, Long> bugFixByDay = employees.stream()
                .filter(e -> "Bug Fix".equals(e.getTaskCategory()))
                .collect(Collectors.groupingBy(
                    e -> e.getDateTime().getDayOfWeek(),
                    Collectors.counting()
                ));
        
        writeBugFixGroupedToCSV(bugFixByDay, "task2_bugfix_by_day.csv");
    }

    static void writeBugFixGroupedToCSV(Map<DayOfWeek, Long> data, String fileName) throws Exception {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("DayOfWeek,Count\n");
            data.forEach((day, count) -> {
                try {
                    writer.write(String.format("%s,%d\n", day, count));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
