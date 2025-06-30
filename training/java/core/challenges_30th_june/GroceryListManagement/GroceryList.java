package challenges_30th_june.GroceryListManagement;

import java.util.ArrayList;
import java.util.Collections;

public class GroceryList {
    private ArrayList<String> groceryList= new ArrayList<>();

    

    public void addItems(String items) {
        String[] itemArray = items.split(",");
        for (String item : itemArray) {
            String trimmed = item.trim();
            if (!trimmed.isEmpty() && !groceryList.contains(trimmed.toLowerCase())) {
                groceryList.add(trimmed.toLowerCase());
            } else {
                System.out.println(trimmed + " is already in the list or invalid.");
            }
        }
        printSortedList();
    }

    public void removeItems(String items) {
        String[] itemArray = items.split(",");
        for (String item : itemArray) {
            String trimmed = item.trim().toLowerCase();
            if (groceryList.remove(trimmed)) {
                System.out.println("Removed: " + trimmed);
            } else {
                System.out.println(trimmed + " not found in the list.");
            }
        }
        printSortedList();
    }

    public void printSortedList() {
        ArrayList<String> sorted = new ArrayList<>(groceryList);
        Collections.sort(sorted);
        System.out.println("Grocery List (Sorted): " + sorted);
    }
}

