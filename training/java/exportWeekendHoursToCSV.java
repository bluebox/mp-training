import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public static void exportWeekendHoursToCSV(Map<String, Double> weekendHours, List<EmployeeWorkLog> logs, String outputFile) {
    try (FileWriter writer = new FileWriter(outputFile)) {
        writer.append("Employee ID,Name,Weekend Hours\n");
        for (var entry : weekendHours.entrySet()) {
            String empId = entry.getKey();
            double hours = entry.getValue();
            String name = logs.stream()
                    .filter(log -> log.getEmployeeId().equals(empId))
                    .map(EmployeeWorkLog::getName)
                    .findFirst()
                    .orElse("Unknown");
            writer.append(String.format("%s,%s,%.2f\n", empId, name, hours));
        }
        System.out.println("Weekend hours exported to " + outputFile);
    } catch (IOException e) {
        System.err.println("Failed to write weekend hours CSV: " + e.getMessage());
    }
}
