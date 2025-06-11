import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public static void exportTop5EmployeesToCSV(List<Map.Entry<String, Double>> top5Employees,
                                            List<EmployeeWorkLog> logs,
                                            String outputFile) {
    try (FileWriter writer = new FileWriter(outputFile)) {
        writer.append("Employee ID,Name,Total Hours (Last 60 Days)\n");
        for (var entry : top5Employees) {
            String empId = entry.getKey();
            double hours = entry.getValue();
            String name = logs.stream()
                    .filter(log -> log.getEmployeeId().equals(empId))
                    .map(EmployeeWorkLog::getName)
                    .findFirst()
                    .orElse("Unknown");
            writer.append(String.format("%s,%s,%.2f\n", empId, name, hours));
        }
        System.out.println("Top 5 employees exported to " + outputFile);
    } catch (IOException e) {
        System.err.println("Failed to write top 5 employees CSV: " + e.getMessage());
    }
}
