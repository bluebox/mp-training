import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public static void exportLowHourDaysToCSV(Map<String, Map<LocalDate, Double>> lowHourDays, List<EmployeeWorkLog> logs, String outputFile) {
    try (FileWriter writer = new FileWriter(outputFile)) {
        writer.append("Employee ID,Name,Date,Total Hours\n");
        for (var empEntry : lowHourDays.entrySet()) {
            String empId = empEntry.getKey();
            String name = logs.stream()
                    .filter(log -> log.getEmployeeId().equals(empId))
                    .map(EmployeeWorkLog::getName)
                    .findFirst()
                    .orElse("Unknown");

            for (var dateEntry : empEntry.getValue().entrySet()) {
                writer.append(String.format("%s,%s,%s,%.2f\n",
                        empId, name, dateEntry.getKey(), dateEntry.getValue()));
            }
        }
        System.out.println("Low hour days exported to " + outputFile);
    } catch (IOException e) {
        System.err.println("Failed to write low hour days CSV: " + e.getMessage());
    }
}
