package dev.tulasidhar.case_study_2_excelfilereading.util;

import java.io.FileWriter;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.tulasidhar.case_study_2_excelfilereading.model.EmployeeWorkLog;

public class TaskFive {
    public static void taskFiveProcess(List<EmployeeWorkLog> employees) throws Exception {
        Map<String, Integer> timePeriodsCount = new HashMap<>();
        timePeriodsCount.put("Morning", 0);   // 6:00 - 12:00
        timePeriodsCount.put("Afternoon", 0); // 12:00 - 17:00
        timePeriodsCount.put("Evening", 0);   // 17:00 - 22:00

        for (EmployeeWorkLog employee : employees) {
            LocalTime time = employee.getDateTime().toLocalTime();
            int hour = time.getHour();

            if (hour >= 6 && hour < 12) {
                timePeriodsCount.put("Morning", timePeriodsCount.get("Morning") + 1);
            } else if (hour >= 12 && hour < 17) {
                timePeriodsCount.put("Afternoon", timePeriodsCount.get("Afternoon") + 1);
            } else if (hour >= 17 && hour < 22) {
                timePeriodsCount.put("Evening", timePeriodsCount.get("Evening") + 1);
            }
        }

        writeTimePeriodsToCSV(timePeriodsCount, "task5_time_periods.csv");
    }

    private static void writeTimePeriodsToCSV(Map<String, Integer> data, String fileName) throws Exception {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("TimePeriod,Count\n");
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                writer.write(String.format("%s,%d\n", entry.getKey(), entry.getValue()));
            }
        }
    }
}
