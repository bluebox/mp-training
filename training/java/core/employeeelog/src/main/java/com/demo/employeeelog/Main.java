package com.demo.employeeelog;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Load employee logs from Excel (implement ExcelReader accordingly)
            String filePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\minchallenges\\employee\\employee_logs.xlsx";
            List<EmployeeWorkLog> logs = ExcelReader.readExcel(filePath);

            System.out.println("Select task to run (1-5):");
            System.out.println("1. Logs from Mar–May; group by project; total hours > 100.");
            System.out.println("2. Group by department and project; sort projects by total time.");
            System.out.println("3. Standard deviation of employee hours per project.");
            System.out.println("4. \"Bug Fix\" tasks grouped by category and day of week.");
            System.out.println("5. Engineering employees working on >2 projects: count unique projects.");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    tasks.task1_MarToMayOver100(logs);
                    System.out.println("Task 1 completed.");
                    break;
                case 2:
                    tasks.task2_DepartmentProjectSorted(logs);
                    System.out.println("Task 2 completed.");
                    break;
                case 3:
                    tasks.task3_StdDevPerProject(logs);
                    System.out.println("Task 3 completed.");
                    break;
                case 4:
                    tasks.task4_BugFixGroupedByDay(logs);
                    System.out.println("Task 4 completed.");
                    break;
                case 5:
                    tasks.task5_EngineeringMultipleProjects(logs);
                    System.out.println("Task 5 completed.");
                    break;
                default:
                    System.out.println("Invalid option. Please select between 1 and 5.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}



