package SetOperations;
import java.util.*;

public class TaskData {
    private static final Set<Task> allTasks = new HashSet<>();

    static {
        allTasks.add(new Task("Alpha", "Setup repo", "Ann", Task.Prior.HIGH, Task.Status.IN_QUEUE));
        allTasks.add(new Task("Alpha", "Implement feature X", "Bob", Task.Prior.MEDIUM, Task.Status.ASSIGNED));
        allTasks.add(new Task("Beta", "Bug fix", "Carol", Task.Prior.LOW, Task.Status.IN_PROGRESS));
        allTasks.add(new Task("Gamma", "Code Review", "Ann", Task.Prior.HIGH, Task.Status.ASSIGNED));
        allTasks.add(new Task("Beta", "Setup CI", "Bob", Task.Prior.MEDIUM, Task.Status.IN_QUEUE));
    }

    public static Set<Task> getTasks(String Assignee) {
        if (Assignee.equalsIgnoreCase("all")) {
            return new HashSet<>(allTasks);
        }

        Set<Task> result = new HashSet<>();
        for (Task task : allTasks) {
            if (task.getAssignee().contains(Assignee)) {
                result.add(task);
            }
        }
        return result;
    }
}
