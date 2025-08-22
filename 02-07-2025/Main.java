package com.prana;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n========= TASK VIEW MENU =========");
            System.out.println("1. View ALL tasks");
            System.out.println("2. View Ann's tasks");
            System.out.println("3. View Bob's tasks");
            System.out.println("4. View Carol's tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("\n========== ALL TASKS ==========");
                    printTasks(TaskData.getTasks("all"));
                    break;
                case "2":
                    System.out.println("\n========== ANN'S TASKS ==========");
                    printTasks(TaskData.getTasks("Ann"));
                    break;
                case "3":
                    System.out.println("\n========== BOB'S TASKS ==========");
                    printTasks(TaskData.getTasks("Bob"));
                    break;
                case "4":
                    System.out.println("\n========== CAROL'S TASKS ==========");
                    printTasks(TaskData.getTasks("Carol"));
                    break;
                case "5":
                    System.out.println("Exiting the program. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please select a number between 1 and 5.");
            }
        }

        scanner.close();
    }

    private static void printTasks(Set<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        int count = 1;
        for (Task task : tasks) {
            System.out.println(count++ + ". " + task);
        }
    }
}
