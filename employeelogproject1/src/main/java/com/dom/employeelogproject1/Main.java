package com.dom.employeelogproject1;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<EmployeeLog> logs = excelread.readExcelFile();

        if (logs.isEmpty()) {
            System.out.println("No logs found. Exiting.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n Employee Log Analysis Menu ");
            System.out.println("1. Urgent or Critical Logs (Save CSV)");
            System.out.println("2. Weekly Effort per Project (Save CSV)");
            System.out.println("3. Track Department Switches (Save CSV)");
            System.out.println("4. Weekend Hours Summary (Save CSV)");
            System.out.println("5. Extract Tags and Count (Save CSV)");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
            case 1:
                tasks.task1_UrgentOrCriticalLogs(logs);
                break;
            case 2:
                tasks.task2_WeeklyEffortPerProject(logs);
                break;
            case 3:
                tasks.task3_TrackDepartmentSwitches(logs);
                break;
            case 4:
                tasks.task4_SummarizeWeekendHours(logs);
                break;
            case 5:
                tasks.task5_ExtractTagsAndCount(logs);
                break;
            case 6:
                System.out.println("Exiting program. Goodbye!");
                scanner.close();
                return;
            default:
                System.out.println("Invalid choice. Please enter a number 1–6.");
                break;
        }

        }
    }
}
