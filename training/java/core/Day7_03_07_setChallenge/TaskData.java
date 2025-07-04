package Day7_03_07_setChallenge;

import java.util.Set;
import java.util.HashSet;

public class TaskData {

    private static Set<Task> ALL_TASKS = new HashSet<>();

    static {
        ALL_TASKS.add(new Task("Rohit", "Mart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH));
        ALL_TASKS.add(new Task("Varma", "Rapido", "This is a transport application", Task.Status.IN_PROGRESS, Task.Priority.MEDIUM));
        ALL_TASKS.add(new Task("Visaal", "Uber", "This is a transport application", Task.Status.NOT_ASSIGNED, Task.Priority.LOW));
        ALL_TASKS.add(new Task("Rohit", "Apollo", "This is a medical application", Task.Status.NOT_ASSIGNED, Task.Priority.LOW));
        ALL_TASKS.add(new Task("Rohit", "Flipkart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH));
        ALL_TASKS.add(new Task("Ann", "Mart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH));
        ALL_TASKS.add(new Task("Ann", "Uber", "This is a transport application", Task.Status.IN_PROGRESS, Task.Priority.MEDIUM));
        ALL_TASKS.add(new Task("Bob", "Rapido", "This is a transport application", Task.Status.IN_PROGRESS, Task.Priority.MEDIUM));
        ALL_TASKS.add(new Task("Bob", "Flipkart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH));
        ALL_TASKS.add(new Task("Carol", "Apollo", "This is a medical application", Task.Status.NOT_ASSIGNED, Task.Priority.LOW));
    }

    public static Set<Task> getTasks(String name) {
    		Set<Task> returnTasks=new HashSet<Task>();
    		for(Task tempTask:ALL_TASKS) {
    			if(tempTask.getAssignedTo().equalsIgnoreCase(name)) {
    				returnTasks.add(tempTask);
    			}
    		}
    		return returnTasks;
    }

    public static Set<Task> getAllTasks() {
        return Set.copyOf(ALL_TASKS); 
    }
}
