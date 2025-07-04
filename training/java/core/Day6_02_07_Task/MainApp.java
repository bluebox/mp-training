package Day6_02_07_Task;

import java.util.Arrays;
import java.util.HashSet;

public class MainApp {
    public static void main(String[] args) {
        Task w1 = new Task("Rohit", "Mart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH);
        Task w2 = new Task("Varma", "Rapido", "This is a transport application", Task.Status.IN_PROGRESS, Task.Priority.MEDIUM);
        Task w3 = new Task("Visaal", "Uber", "This is a transport application", Task.Status.NOT_ASSIGNED, Task.Priority.LOW);
        Task w4 = new Task("Rohit", "Apollo", "This is a medical application", Task.Status.NOT_ASSIGNED, Task.Priority.LOW);
        Task w5 = new Task("Rohit", "Flipkart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH);

        HashSet<Task> itemSet = new HashSet<>(Arrays.asList(w1, w2, w3, w4, w5));
        WorkManager manager = new WorkManager(itemSet);

        System.out.println("========== All Work Items ==========");
        manager.displayAll();

        String user = "Rohit";
        System.out.printf("\n========== Tasks Assigned to %s ==========\n", user);
        manager.displayByAssignee(user);

        System.out.println("\n========== Sorted Work Items ==========");
        manager.displaySorted();
    }
}
