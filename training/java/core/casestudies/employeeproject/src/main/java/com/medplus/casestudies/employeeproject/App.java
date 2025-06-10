package com.medplus.casestudies.employeeproject;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ExcelReader reader = new ExcelReader();
        List<EmployeeWorkLog> logs = reader.readWorkLogs("src/main/java/com/medplus/casestudies/employeeproject/Sample_Employee_WorkLogs.xlsx");

        EmployeeAnalyzer analyzer = new EmployeeAnalyzer();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Employee Productivity & Analytics System ===");
            System.out.println("1. Calculate Average Weekly Hours");
            System.out.println("2. Daily Average Trend (Last 30 Working Days)");
            System.out.println("3. Find 3+ Consecutive Zero-Hour Days");
            System.out.println("4. Days with < 2 Hours (Grouped)");
            System.out.println("5. Employees Who Switched Projects More Than Once/Month");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
            case 1:
                analyzer.calculateWeeklyAverages(logs);
                break;
            case 2:
                analyzer.calculateDailyTrendLast30Days(logs);
                break;
            case 3:
                analyzer.findZeroHourStreaks(logs);
                break;
            case 4:
                analyzer.findLessThanTwoHoursDays(logs);
                break;
            case 5:
                analyzer.findProjectHoppers(logs);
                break;
            case 6:
                System.out.println("Exiting program.");
                scanner.close();
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }

        }
    }
}
