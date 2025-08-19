package dev.tulasidhar.case_study_2_excelfilereading.util;


import dev.tulasidhar.case_study_2_excelfilereading.model.EmployeeWorkLog;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class EmployeeGenerator {
    private static final String[] FIRST_NAMES = {"Dasu", "Saketh", "Vardhan", "Kaushik", "Tulasidhar", "Ravi", "Suresh", "Anil", "Rajesh"};
    private static final String[] LAST_NAMES = {"Reddy", "Kumar", "Patel", "Sharma", "Gupta"};
    private static final String[] DEPARTMENTS = {"HR", "IT", "Finance", "Marketing"};
    private static final String[] PROJECT_IDS = {"P101", "P102", "P103", "P104"};
    private static final String[] TASK_CATEGORIES = {"Development", "Testing", "Bug Fix", "Documentation"};
    private static final String[] REMARKS = {"Completed", "In Progress", "Delayed", "On Hold"};

    public static List<EmployeeWorkLog> generateEmployees(int count) {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        Random rand = new Random();
        for (int i = 1; i <= count; i++) {
            String employeeId = "E" + (2025000 + i);
            String firstName = FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
            String name = firstName + " " + lastName;
            String department = DEPARTMENTS[rand.nextInt(DEPARTMENTS.length)];
            String projectId = PROJECT_IDS[rand.nextInt(PROJECT_IDS.length)];
            
            LocalDateTime dateTime = LocalDateTime.of(2025, 1, 1, 0, 0)
                    .plusDays(rand.nextInt(365))
                    .plusHours(6 + rand.nextInt(16))
                    .plusMinutes(rand.nextInt(60));

            String taskCategory = TASK_CATEGORIES[rand.nextInt(TASK_CATEGORIES.length)];
            double hoursWorked = 1 + rand.nextDouble() * 200;
            String remarks = REMARKS[rand.nextInt(REMARKS.length)];
            logs.add(new EmployeeWorkLog(employeeId, name, department, projectId, dateTime, taskCategory, hoursWorked, remarks));
        }
        return logs;
    }
}