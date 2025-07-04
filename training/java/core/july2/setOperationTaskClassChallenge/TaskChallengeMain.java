package dev.tulasidhar.july2.setOperationTaskClassChallenge;

import java.util.Set;

public class TaskChallengeMain {
    public static void main(String[] args) {
        
        TaskData taskData = new TaskData();
        
        
        System.out.println("All tasks in the system:");
        Set<Task> allTasks = taskData.getTasks("all");
        allTasks.forEach(System.out::println);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        String[] employees = {"Manager", "Ann", "Bob", "Carol"};
        
        for (String employee : employees) {
            System.out.println("Tasks for " + employee + ":");
            Set<Task> employeeTasks = taskData.getTasks(employee);
            if (employeeTasks.isEmpty()) {
                System.out.println("   No tasks found for " + employee);
            } else {
                employeeTasks.forEach(task -> System.out.println("   " + task));
            }
            System.out.println();
        }
        
        System.out.println("=".repeat(50) + "\n");
        
        
        
    }
}