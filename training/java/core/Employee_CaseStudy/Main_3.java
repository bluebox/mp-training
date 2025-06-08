import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main_3 {

    public static void main(String[] args) {
        List<EmployeeWorkLogs> workLogs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader("Sample_Employee_WorkLogs.csv"))) {
          
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

        List<EmployeeWorkLogs> uniqueLogs = new ArrayList<>();
        for (EmployeeWorkLogs log : workLogs) {
            boolean isDuplicate = false;
            for (EmployeeWorkLogs uniqueLog : uniqueLogs) {
                if (log.getEmployeeId().equals(uniqueLog.getEmployeeId()) &&
                        log.getDate().equals(uniqueLog.getDate()) &&
                        log.getProjectId().equals(uniqueLog.getProjectId())) {

                    isDuplicate = true;
                    // print duplicate log
                    System.out.printf("Duplicate log found: %s, %s, %s, %s, %s, %s, %.2f, %s%n",
                            log.getEmployeeId(),
                            log.getName(),
                            log.getDepartment(),
                            log.getProjectId(),
                            log.getDate(),
                            log.getTaskCategory(),
                            log.getHoursWorked(),
                            log.getRemarks());
                    break;
                }
            }
            if (!isDuplicate) {
                uniqueLogs.add(log);
            }
        }
        Map<String, List<String>> engineeringProjects = new HashMap<>();
        for (EmployeeWorkLogs log : uniqueLogs) {
            if ("Engineering".equalsIgnoreCase(log.getDepartment())) {
                engineeringProjects
                    .computeIfAbsent(log.getEmployeeId(), k -> new ArrayList<>())
                    .add(log.getProjectId());
            }
        }

        System.out.println("Engineering employees working on more than 2 projects:");
        //enter them in new file with all details in csv file
        try (PrintWriter writer = new PrintWriter("Engineering_Employees_Projects.csv")) {
            writer.println("Employee ID, Projects");
            for (Map.Entry<String, List<String>> entry : engineeringProjects.entrySet()) {
                List<String> projects = entry.getValue().stream().distinct().collect(Collectors.toList());
                if (projects.size() > 2) {
                    String projectsStr = String.join(", ", projects);
                    writer.printf("%s, %s%n", entry.getKey(), projectsStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, List<String>> entry : engineeringProjects.entrySet()) {
            List<String> projects = entry.getValue().stream().distinct().collect(Collectors.toList());
            if (projects.size() > 2) {
                System.out.printf("Employee ID: %s, Projects: %s%n", entry.getKey(), projects);
            }
        }
       
    }
}
