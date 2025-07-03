package Day6_02_07_Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class WorkManager {

    private HashSet<WorkItem> itemSet;

    public WorkManager(HashSet<WorkItem> itemSet) {
        this.itemSet = itemSet;
    }

    public void displayAll() {
        itemSet.forEach(System.out::println);
    }

    public void displayByAssignee(String name) {
        itemSet.stream()
               .filter(item -> item.getAssignedTo().equals(name))
               .forEach(System.out::println);
    }

    public void displaySorted() {
        ArrayList<WorkItem> sorted = new ArrayList<>(itemSet);
        sorted.sort(Comparator.comparing(WorkItem::getProjectName)
                              .thenComparing(WorkItem::getTaskDetails));
        sorted.forEach(System.out::println);
    }
}
