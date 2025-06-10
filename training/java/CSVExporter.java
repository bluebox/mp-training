import java.io.FileWriter;
import java.io.IOException;

public class CSVExporter {

    public static void exportAvgWeeklyHoursToCSV() {
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.append("Employee ID,Name,Month,Average Weekly Hours\n");

            for (var empEntry : avgWeeklyHours.entrySet()) {
                String empId = empEntry.getKey();
                String name = logs.stream()
                        .filter(log -> log.getEmployeeId().equals(empId))
                        .map(EmployeeWorkLog::getName)
                        .findFirst()
                        .orElse("Unknown");

                for (var monthEntry : empEntry.getValue().entrySet()) {
                    writer.append(String.format("%s,%s,%s,%.2f\n", empId, name, monthEntry.getKey(), monthEntry.getValue()));
                }
            }
            System.out.println("Average weekly hours exported to " + outputFile);
        } catch (IOException e) {
            System.err.println("Failed to write average weekly hours CSV: " + e.getMessage());
        }
    }
}