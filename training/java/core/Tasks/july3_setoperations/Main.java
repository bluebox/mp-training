package corejava.july3_setoperations;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Task> masterTaskList = TaskData.getAllTasks();
        Set<Task> annTasks = TaskData.getAnnTasks();
        Set<Task> bobTasks = TaskData.getBobTasks();
        Set<Task> carolTasks = TaskData.getCarolTasks();

        System.out.println("--- Full Task List ---");
        Set<Task> fullList = TaskAnalyzer.getFullTaskList(masterTaskList, annTasks, bobTasks, carolTasks);
        fullList.forEach(System.out::println);
        System.out.println("Total tasks in full list: " + fullList.size());

        System.out.println("--- Tasks Assigned to at Least One Team Member ---");
        Set<Task> assignedToAtLeastOne = TaskAnalyzer.getTasksAssignedToAtLeastOneTeamMember(annTasks, bobTasks, carolTasks);
        assignedToAtLeastOne.forEach(System.out::println);
        System.out.println("Total tasks assigned to at least one: " + assignedToAtLeastOne.size());

        System.out.println("--- Tasks Still Need to Be Assigned ---");
        Set<Task> unassignedTasks = TaskAnalyzer.getTasksStillNeedingAssignment(masterTaskList, annTasks, bobTasks, carolTasks);
        unassignedTasks.forEach(System.out::println);
        System.out.println("Total tasks still needing assignment: " + unassignedTasks.size());

        System.out.println("--- Tasks Assigned to Multiple Employees ---");
        Set<Task> tasksAssignedToMultiple = TaskAnalyzer.getTasksAssignedToMultipleEmployees(annTasks, bobTasks, carolTasks);
        tasksAssignedToMultiple.forEach(System.out::println);
        System.out.println("Total tasks assigned to multiple: " + tasksAssignedToMultiple.size());
    }
}