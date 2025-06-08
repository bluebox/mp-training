import java.io.BufferedReader;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<EmployeeWorkLogs> workLogs = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new java.io.FileReader("Sample_Employee_WorkLogs.csv"))) {
            
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String employeeId = parts[0].trim();
                    String name = parts[1].trim();
                    String department = parts[2].trim();
                    String projectId = parts[3].trim();
                    LocalDate date = LocalDate.parse(parts[4].trim());
                    String taskCategory = parts[5].trim();
                    double hoursWorked = Double.parseDouble(parts[6].trim());
                    String remarks = parts[7].trim();

                    EmployeeWorkLogs log = new EmployeeWorkLogs(employeeId, name, department, projectId, date, taskCategory, hoursWorked, remarks);
                    workLogs.add(log);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Task 1:Detect and remove duplicate logs. 
        List<EmployeeWorkLogs> uniqueLogs = new ArrayList<>();
        for (EmployeeWorkLogs log : workLogs) {
            boolean isDuplicate = false;
            for (EmployeeWorkLogs uniqueLog : uniqueLogs) {
                if (log.getEmployeeId().equals(uniqueLog.getEmployeeId()) &&
                    log.getDate().equals(uniqueLog.getDate()) &&
                    log.getProjectId().equals(uniqueLog.getProjectId())) {

                    isDuplicate = true;
                    System.out.printf("Duplicate log found: %s, %s, %s, %s, %s, %s, %.2f, %s%n",
                        log.getEmployeeId(),
                        log.getName(),
                        log.getDepartment(),
                        log.getProjectId(),
                        log.getDate(),
                        log.getTaskCategory(),
                        log.getHoursWorked(),
                        log.getRemarks()
                    );
                    break;
                }
            }
            if (!isDuplicate) {
                uniqueLogs.add(log);
            }
        }
        try (PrintWriter writer = new PrintWriter("Unique_Employee_WorkLogs.csv")) {
            
            writer.println("EmployeeId,Name,Department,ProjectId,Date,TaskCategory,HoursWorked,Remarks");
            for (EmployeeWorkLogs log : uniqueLogs) {
                writer.printf("%s,%s,%s,%s,%s,%s,%.2f,%s%n",
                    log.getEmployeeId(),
                    log.getName(),
                    log.getDepartment(),
                    log.getProjectId(),
                    log.getDate(),
                    log.getTaskCategory(),
                    log.getHoursWorked(),
                    log.getRemarks()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
