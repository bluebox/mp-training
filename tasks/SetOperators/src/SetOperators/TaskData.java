package SetOperators;
import java.util.*;

public class TaskData {
    private static final Map<String, Set<Task>> tasks = new HashMap<>();

    static {
        Set<Task> allTasks = new HashSet<>();
        Set<Task> annTasks = new HashSet<>();
        Set<Task> bobTasks = new HashSet<>();
        Set<Task> carolTasks = new HashSet<>();

        allTasks.add(new Task("Project Alpha", "Implement new feature"));
        allTasks.add(new Task("Project Alpha", "Fix bug"));
        allTasks.add(new Task("Project Beta", "Develop new module"));
        allTasks.add(new Task("Project Gamma", "Test new functionality"));

        annTasks.add(new Task("Project Alpha", "Implement new feature"));
        annTasks.add(new Task("Project Beta", "Develop new module"));

        bobTasks.add(new Task("Project Alpha", "Fix bug"));
        bobTasks.add(new Task("Project Gamma", "Test new functionality"));

        carolTasks.add(new Task("Project Alpha", "Implement new feature"));
        carolTasks.add(new Task("Project Gamma", "Test new functionality"));

        tasks.put("all", allTasks);
        tasks.put("Ann", annTasks);
        tasks.put("Bob", bobTasks);
        tasks.put("Carol", carolTasks);
    }

    public static Set<Task> getTasks(String employee) {
        if (tasks.containsKey(employee)) {
            return tasks.get(employee);
        } else if (employee.equalsIgnoreCase("all")) {
            return tasks.get("all");
        } else {
            return Collections.emptySet();
        }
    }
}
