package com.prana;

import java.util.*;

import com.prana.Task.Priority;
import com.prana.Task.Status;

public class TaskData {

    public static Set<Task> getTasks(String assigneeFilter) {
        Set<Task> allTasks = new TreeSet<>();

        
        allTasks.add(new Task("Manager", "Infrastructure", "Logging", Priority.HIGH, Status.NOT_ASSIGNED));
        allTasks.add(new Task("Manager", "Infrastructure", "DB Access", Priority.MEDIUM, Status.NOT_ASSIGNED));
        allTasks.add(new Task("Manager", "Infrastructure", "Security", Priority.HIGH, Status.NOT_ASSIGNED));
        allTasks.add(new Task("Manager", "Infrastructure", "Password Policy", Priority.MEDIUM, Status.NOT_ASSIGNED));
        allTasks.add(new Task("Manager", "Data Design", "Task Table", Priority.MEDIUM, Status.NOT_ASSIGNED));
        allTasks.add(new Task("Manager", "Data Design", "Employee Table", Priority.MEDIUM, Status.NOT_ASSIGNED));
        allTasks.add(new Task("Manager", "Data Design", "Cross Reference Tables", Priority.HIGH, Status.NOT_ASSIGNED));
        allTasks.add(new Task("Manager", "Data Design", "Encryption Policy", Priority.HIGH, Status.NOT_ASSIGNED));
        allTasks.add(new Task("Manager", "Data Access", "Write Views", Priority.LOW, Status.NOT_ASSIGNED));
        allTasks.add(new Task("Manager", "Data Access", "Set Up Users", Priority.LOW, Status.NOT_ASSIGNED));
        allTasks.add(new Task("Manager", "Data Access", "Set Up Access Policy", Priority.LOW, Status.NOT_ASSIGNED));

        
        if (assigneeFilter.equalsIgnoreCase("Ann") || assigneeFilter.equalsIgnoreCase("all")) {
            allTasks.add(new Task("Ann", "Infrastructure", "Security", Priority.HIGH, Status.IN_PROGRESS));
            allTasks.add(new Task("Ann", "Infrastructure", "Password Policy", Priority.MEDIUM, Status.IN_PROGRESS));
            allTasks.add(new Task("Ann", "Research", "Cloud solutions", Priority.MEDIUM, Status.IN_PROGRESS));
            allTasks.add(new Task("Ann", "Data Design", "Encryption Policy", Priority.HIGH, Status.ASSIGNED));
            allTasks.add(new Task("Ann", "Data Design", "Project Table", Priority.MEDIUM, Status.ASSIGNED));
            allTasks.add(new Task("Ann", "Data Access", "Write Views", Priority.LOW, Status.IN_PROGRESS));
        }

        
        if (assigneeFilter.equalsIgnoreCase("Bob") || assigneeFilter.equalsIgnoreCase("all")) {
              allTasks.add(new Task("Bob", "Infrastructure", "Security", Priority.HIGH, Status.IN_PROGRESS));
              allTasks.add(new Task("Bob", "Infrastructure", "Password Policy", Priority.MEDIUM, Status.ASSIGNED));
              allTasks.add(new Task("Bob", "Data Design", "Encryption Policy", Priority.HIGH, Status.ASSIGNED));
              allTasks.add(new Task("Bob", "Data Access", "Write Views", Priority.LOW, Status.IN_PROGRESS));
        }

        
        if (assigneeFilter.equalsIgnoreCase("Carol") || assigneeFilter.equalsIgnoreCase("all")) {
            allTasks.add(new Task("Carol", "Infrastructure", "Logging", Priority.HIGH, Status.IN_PROGRESS));
            allTasks.add(new Task("Carol", "Infrastructure", "DB Access", Priority.MEDIUM, Status.ASSIGNED));
            allTasks.add(new Task("Carol", "Infrastructure", "Password Policy", Priority.MEDIUM, Status.ASSIGNED));
            allTasks.add(new Task("Carol", "Data Design", "Task Table", Priority.HIGH, Status.ASSIGNED));
            allTasks.add(new Task("Carol", "Data Access", "Write Views", Priority.LOW, Status.ASSIGNED));
        }

        return allTasks;
    }
}
