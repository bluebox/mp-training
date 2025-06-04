package Collections;

import java.util.*;

enum Status {
    IN_QUEUE, ASSIGNED, IN_PROGRESS
}

enum Priority {
    HIGH, MED, LOW
}

class Task implements Comparable<Task> {
    private String assignee;
    private String project;
    private String description;
    private Status status;
    private Priority priority;

    public Task(String assignee, String project, String description, Status status, Priority priority) {
        this.assignee = assignee;
        this.project = project;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(project, task.project) &&
               Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, description);
    }

    @Override
    public int compareTo(Task o) {
        int projCmp = this.project.compareTo(o.project);
        return (projCmp != 0) ? projCmp : this.description.compareTo(o.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%s, %s)", assignee, project, description, status, priority);
    }
}

class TaskData {
    private static final Set<Task> allTasks = new HashSet<>();

    static {
        allTasks.add(new Task("Ann", "Alpha", "Design DB", Status.IN_PROGRESS, Priority.HIGH));
        allTasks.add(new Task("Ann", "Alpha", "UI Design", Status.ASSIGNED, Priority.MED));
        allTasks.add(new Task("Bob", "Beta", "Setup CI", Status.IN_QUEUE, Priority.LOW));
        allTasks.add(new Task("Carol", "Gamma", "API Dev", Status.ASSIGNED, Priority.HIGH));
        allTasks.add(new Task("Bob", "Alpha", "Testing", Status.IN_PROGRESS, Priority.MED));
        allTasks.add(new Task("Carol", "Delta", "Deploy", Status.IN_QUEUE, Priority.HIGH));
        allTasks.add(new Task("Ann", "Gamma", "API Dev", Status.IN_PROGRESS, Priority.LOW));
    }

    public static Set<Task> getTasks(String name) {
        if (name.equalsIgnoreCase("all")) return new HashSet<>(allTasks);
        Set<Task> result = new HashSet<>();
        for (Task t : allTasks) {
            if (t.getAssignee().equalsIgnoreCase(name)) {
                result.add(t);
            }
        }
        return result;
    }
}

public class TaskManager {
    public static Set<Task> getUnion(Set<Task> a, Set<Task> b) {
        Set<Task> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    public static Set<Task> getDifference(Set<Task> a, Set<Task> b) {
        Set<Task> result = new HashSet<>(a);
        result.removeAll(b);
        return result;
    }

    public static Set<Task> getIntersect(Set<Task> a, Set<Task> b) {
        Set<Task> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    public static void main(String[] args) {
        Set<Task> annTasks = TaskData.getTasks("Ann");
        Set<Task> bobTasks = TaskData.getTasks("Bob");
        Set<Task> carolTasks = TaskData.getTasks("Carol");
        Set<Task> allTasks = TaskData.getTasks("all");

        System.out.println("Union of Ann and Bob:");
        getUnion(annTasks, bobTasks).forEach(System.out::println);

        System.out.println("\nDifference (Ann - Bob):");
        getDifference(annTasks, bobTasks).forEach(System.out::println);

        System.out.println("\nIntersection of Ann and Carol:");
        getIntersect(annTasks, carolTasks).forEach(System.out::println);

        System.out.println("\nAll Tasks:");
        allTasks.forEach(System.out::println);
    }
}

