import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main_4 {
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

        List<EmployeeWorkLogs> departmentSwitches = new ArrayList<>();
        for (EmployeeWorkLogs log : workLogs) {
            boolean isSwitch = false;
            for (EmployeeWorkLogs previousLog : departmentSwitches) {
                if (log.getEmployeeId().equals(previousLog.getEmployeeId()) &&
                        log.getDate().getMonth() == previousLog.getDate().getMonth() &&
                        !log.getDepartment().equals(previousLog.getDepartment())) {
                    isSwitch = true;
                    break;
                }
            }
            if (isSwitch) {
                departmentSwitches.add(log);
                System.out.printf("Department switch detected: %s, %s, %s, %s, %s, %s, %.2f, %s%n",
                        log.getEmployeeId(),
                        log.getName(),
                        log.getDepartment(),
                        log.getProjectId(),
                        log.getDate(),
                        log.getTaskCategory(),
                        log.getHoursWorked(),
                        log.getRemarks());
            }
        }
        if (departmentSwitches.isEmpty()) {
            System.out.println("No department switches detected.");
        } else {
            System.out.println("Department switches detected:");
            for (EmployeeWorkLogs switchLog : departmentSwitches) {
                System.out.printf("%s, %s, %s, %s, %s, %s, %.2f, %s%n",
                        switchLog.getEmployeeId(),
                        switchLog.getName(),
                        switchLog.getDepartment(),
                        switchLog.getProjectId(),
                        switchLog.getDate(),
                        switchLog.getTaskCategory(),
                        switchLog.getHoursWorked(),
                        switchLog.getRemarks());
            }
        }


    }
}
