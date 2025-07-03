package Day6_02_07_Task;

import java.util.Arrays;
import java.util.HashSet;

public class MainApp {
    public static void main(String[] args) {
        WorkItem w1 = new WorkItem("Rohit", "Mart", "This is an e-commerce application", WorkItem.Status.ASSIGNED, WorkItem.Priority.HIGH);
        WorkItem w2 = new WorkItem("Varma", "Rapido", "This is a transport application", WorkItem.Status.IN_PROGRESS, WorkItem.Priority.MEDIUM);
        WorkItem w3 = new WorkItem("Visaal", "Uber", "This is a transport application", WorkItem.Status.NOT_ASSIGNED, WorkItem.Priority.LOW);
        WorkItem w4 = new WorkItem("Rohit", "Apollo", "This is a medical application", WorkItem.Status.NOT_ASSIGNED, WorkItem.Priority.LOW);
        WorkItem w5 = new WorkItem("Rohit", "Flipkart", "This is an e-commerce application", WorkItem.Status.ASSIGNED, WorkItem.Priority.HIGH);

        HashSet<WorkItem> itemSet = new HashSet<>(Arrays.asList(w1, w2, w3, w4, w5));
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
