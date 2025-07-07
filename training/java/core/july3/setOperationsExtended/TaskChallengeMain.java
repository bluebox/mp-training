package dev.tulasidhar.july3.setOperationsExtended;

import java.util.Set;

public class TaskChallengeMain {
    public static void main(String[] args) {
        
        TaskData taskData = new TaskData();
        
        
       
        
        String[] employees = {"all", "Dasu", "Zoro", "Guts"};
        
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
        
        //atleast one of the members
        
        Set<Task> assignedToAtleastOne = taskData.getTasksAssignedToAtleastOne();
        System.out.println("Tasks assigned to atleast one");
        assignedToAtleastOne.forEach(task-> System.out.println(" "+task));
        
        System.out.println("=".repeat(50) + "\n");
        
        
        //not assigned at all
        
        Set<Task> assignedToNone = taskData.getTasksAssignedToNone();
        System.out.println("Tasks assigned to no one");
        assignedToNone.forEach(task-> System.out.println(" "+task));
        
        System.out.println("=".repeat(50) + "\n");
        
        //assigned to multiple employees
        Set<Task> assignedToMultiple = taskData.getTasksAssignedToMultiple();
        System.out.println("Tasks assigned to Multiple");
        assignedToMultiple.forEach(task-> System.out.println(" "+task));

    } 
}