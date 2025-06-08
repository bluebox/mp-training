import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_2 {

    public static void main(String[] args) {
        List<EmployeeWorkLogs> workLogs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader("Sample_Employee_WorkLogs.csv"))) {
            // Skip the header line
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

                    EmployeeWorkLogs log = new EmployeeWorkLogs(employeeId, name, department, projectId, date,
                            taskCategory, hoursWorked, remarks);
                    workLogs.add(log);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        //uniqueLogs
        List<EmployeeWorkLogs> uniqueLogs = new ArrayList<>();
        for (EmployeeWorkLogs log : workLogs) {
            boolean isDuplicate = false;
            for (EmployeeWorkLogs uniqueLog : uniqueLogs) {
                if (log.getEmployeeId().equals(uniqueLog.getEmployeeId()) &&
                    log.getDate().equals(uniqueLog.getDate()) &&
                    log.getProjectId().equals(uniqueLog.getProjectId())) {

                    isDuplicate = true;
                    //print duplicate log
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

        // Task 2: Detect >9 hrs/day logs; group by employee and date
        

        Map<String, Map<LocalDate, Double>> hoursByEmployeeDate = new HashMap<>();
        for (EmployeeWorkLogs log : uniqueLogs) {
        
            hoursByEmployeeDate
                .computeIfAbsent(log.getEmployeeId(), k -> new HashMap<>())
                .merge(log.getDate(), log.getHoursWorked(), Double::sum);
        }

        try (PrintWriter writer = new PrintWriter("Over9HoursLogs.csv")) {
            // Write header
            writer.println("EmployeeId,Name,Department,ProjectId,Date,TaskCategory,HoursWorked,Remarks");
            for (EmployeeWorkLogs log : uniqueLogs) {
                double totalHours = hoursByEmployeeDate
                    .getOrDefault(log.getEmployeeId(), new HashMap<>())
                    .getOrDefault(log.getDate(), 0.0);
                if (totalHours > 9.0) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Grouping by employee and date, summing up hours worked
        Map<String, Map<LocalDate, List<EmployeeWorkLogs>>> groupedLogs = new HashMap<>();
        for (EmployeeWorkLogs log : uniqueLogs) {
            if (log.getHoursWorked() > 9) {
                groupedLogs
                    .computeIfAbsent(log.getEmployeeId(), k -> new HashMap<>())
                    .computeIfAbsent(log.getDate(), k -> new ArrayList<>())
                    .add(log);
            }
        }
        

       

        
    }

}
