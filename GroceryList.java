import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GroceryList {

    private static ArrayList<String> groceryList = new ArrayList<>();

    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
        	
            System.out.println("\nChoose an action: add, remove, print, quit");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
            
                case "add":
                	
                    System.out.print("Enter item to add: ");
                    String itemToAdd = scanner.nextLine();
                    addItem(itemToAdd);
                    break;
                    
                case "remove":
                	
                    System.out.print("Enter item to remove: ");
                    String itemToRemove = scanner.nextLine();
                    removeItem(itemToRemove);
                    break;
                    
                case "print":
                	
                    printList();
                    break;
                    
                case "quit":
                	
                    quit = true;
                    break;
                    
                default:
                	
                    System.out.println("Invalid action. Try again.");
            }
        }
    }
    
    private static void addItem(String item) {
    	
        if (!groceryList.contains(item.toLowerCase())) {
            groceryList.add(item.toLowerCase());
            System.out.println(item + " added.");
        } 
        else {
            System.out.println("Item already exists. Not added.");
        }
        
        printList();
    }

    private static void removeItem(String item) {
    	
        if (groceryList.remove(item.toLowerCase())) {
            System.out.println(item + " removed.");
        } 
        else {
            System.out.println("Item not found.");
        }
        printList();
    }

    private static void printList() {
    	
        ArrayList<String> sortedList = new ArrayList<>(groceryList);
        Collections.sort(sortedList);
        System.out.println("Current Grocery List: " + sortedList);
    }
}


