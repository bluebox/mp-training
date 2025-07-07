import java.util.*;
public class TaskData {
    private static Set<Task> allTasks = new TreeSet<>();
    static{
        allTasks.add(new Task("Akash", "Java", "Learn java", Task.Status.IN_PROGRESS, Task.Priority.HIGH));
        allTasks.add(new Task("Akash", "JDBC", "Learn JDBC", Task.Status.ASSIGNED, Task.Priority.MEDIUM));
        allTasks.add(new Task("B", "App Design", "Push notifications", Task.Status.ASSIGNED, Task.Priority.HIGH));
        allTasks.add(new Task("B", "Database", "Optimize queries", Task.Status.ASSIGNED, Task.Priority.LOW));
        allTasks.add(new Task("C", "API", "learn", Task.Status.IN_PROGRESS, Task.Priority.HIGH));
        allTasks.add(new Task("C", "HTML & CSS", "CSS styling", Task.Status.IN_PROGRESS, Task.Priority.MEDIUM));
    }      
    public static Set<Task> getTasks(String name){
        if(name.equalsIgnoreCase("all")){
            return new TreeSet<>(allTasks);
        }
        Set<Task> filtered=new TreeSet<>();
        for (Task task: allTasks){
            if (name.equalsIgnoreCase(task.getAssignee())){
                filtered.add(task);
            }
        }
        return filtered;
    }
}
