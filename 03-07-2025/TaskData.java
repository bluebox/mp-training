package com.prana;

import java.util.*;

public class TaskData {

    private static final Set<Task> allTasks = Set.of(
        new Task("Infrastructure", "Logging", null, Priority.HIGH, null),
        new Task("Infrastructure", "DB Access", null, Priority.MEDIUM, null),
        new Task("Infrastructure", "Security", null, Priority.HIGH, null),
        new Task("Infrastructure", "Password Policy", null, Priority.MEDIUM, null),
        new Task("Data Design", "Task Table", null, Priority.MEDIUM, null),
        new Task("Data Design", "Employee Table", null, Priority.MEDIUM, null),
        new Task("Data Design", "Cross Reference Tables", null, Priority.HIGH, null),
        new Task("Data Design", "Encryption Policy", null, Priority.HIGH, null),
        new Task("Data Access", "Write Views", null, Priority.LOW, null),
        new Task("Data Access", "Set Up Users", null, Priority.LOW, null),
        new Task("Data Access", "Set Up Access Policy", null, Priority.LOW, null)
    );

    private static final Set<Task> annTasks = Set.of(
        new Task("Infrastructure", "Security", "Ann", Priority.HIGH, Status.IN_PROGRESS),
        new Task("Infrastructure", "Password Policy", "Ann", Priority.MEDIUM, Status.IN_PROGRESS),
        new Task("Research", "Cloud solutions", "Ann", Priority.MEDIUM, Status.IN_PROGRESS),
        new Task("Data Design", "Encryption Policy", "Ann", Priority.HIGH, null),
        new Task("Data Design", "Project Table", "Ann", Priority.MEDIUM, null),
        new Task("Data Access", "Write Views", "Ann", Priority.LOW, Status.IN_PROGRESS)
    );

    private static final Set<Task> bobTasks = Set.of(
        new Task("Infrastructure", "Security", "Bob", Priority.HIGH, Status.IN_PROGRESS),
        new Task("Infrastructure", "Password Policy", "Bob", Priority.MEDIUM, null),
        new Task("Data Design", "Encryption Policy", "Bob", Priority.HIGH, null),
        new Task("Data Access", "Write Views", "Bob", Priority.LOW, Status.IN_PROGRESS)
    );

    private static final Set<Task> carolTasks = Set.of(
        new Task("Infrastructure", "Logging", "Carol", Priority.HIGH, Status.IN_PROGRESS),
        new Task("Infrastructure", "DB Access", "Carol", Priority.MEDIUM, null),
        new Task("Infrastructure", "Password Policy", "Carol", Priority.MEDIUM, null),
        new Task("Data Design", "Task Table", "Carol", Priority.HIGH, null),
        new Task("Data Access", "Write Views", "Carol", Priority.LOW, null)
    );

    public static Set<Task> getTasks(String name) {
        return switch (name.toLowerCase()) {
            case "ann" -> Set.copyOf(annTasks);
            case "bob" -> Set.copyOf(bobTasks);
            case "carol" -> Set.copyOf(carolTasks);
            case "all" -> {
                Set<Task> all = new HashSet<>(allTasks);
                all.addAll(annTasks);
                all.addAll(bobTasks);
                all.addAll(carolTasks);
                yield all;
            }
            default -> Set.of();
        };
    }

    public static Set<Task> getMasterTasks() {
        return Set.copyOf(allTasks);
    }
}
