import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TaskData {
    private Set<Task> allTasks;
    private Set<Task> managerTasks;
    private Set<Task> annTasks;
    private Set<Task> bobTasks;
    private Set<Task> carolTasks;

    public TaskData() {
        this.allTasks = new TreeSet<>();
        this.managerTasks = new TreeSet<>();
        this.annTasks = new TreeSet<>();
        this.bobTasks = new TreeSet<>();
        this.carolTasks = new TreeSet<>();

        Task task1 = new Task("Ann", "Project Alpha", "Develop UI", "In Progress", "High");
        Task task2 = new Task("Bob", "Project Alpha", "Implement Backend", "Assigned", "High");
        Task task3 = new Task("Carol", "Project Beta", "Write Documentation", "Not Yet Assigned", "Medium");
        Task task4 = new Task("Ann", "Project Gamma", "Test Module", "Assigned", "High");
        Task task5 = new Task("Manager", "Project Delta", "Review Reports", "In Progress", "High");
        Task task6 = new Task("Bob", "Project Beta", "Fix Bug #123", "In Progress", "High");
        Task task7 = new Task("Ann", "Project Alpha", "Refactor Code", "Not Yet Assigned", "Medium");
        Task task8 = new Task("Carol", "Project Delta", "Prepare Presentation", "Assigned", "Medium");
        Task task9 = new Task("Manager", "Project Alpha", "Client Meeting", "Assigned", "High");

        Collections.addAll(allTasks, task1, task2, task3, task4, task5, task6, task7, task8, task9);

        managerTasks.add(task5);
        managerTasks.add(task9);

        annTasks.add(task1);
        annTasks.add(task4);
        annTasks.add(task7);

        bobTasks.add(task2);
        bobTasks.add(task6);

        carolTasks.add(task3);
        carolTasks.add(task8);
    }

    public Set<Task> getTasks(String assigneeName) {
        switch (assigneeName.toLowerCase()) {
            case "all":
                return new TreeSet<>(allTasks);
            case "manager":
                return new TreeSet<>(managerTasks);
            case "ann":
                return new TreeSet<>(annTasks);
            case "bob":
                return new TreeSet<>(bobTasks);
            case "carol":
                return new TreeSet<>(carolTasks);
            default:
                System.out.println("No predefined tasks for assignee: " + assigneeName);
                return new TreeSet<>();
        }
    }

    public Set<Task> getTasksFiltered(String assigneeName) {
        if ("all".equalsIgnoreCase(assigneeName)) {
            return new TreeSet<>(allTasks);
        }
        Set<Task> filteredTasks = new TreeSet<>();
        for (Task task : allTasks) {
            if (task.getAssignee().equalsIgnoreCase(assigneeName)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}