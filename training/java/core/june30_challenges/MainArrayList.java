package june30_collections;

public class MainArrayList {
    static GroceryList groceryList = new GroceryList();

    public static void main(String[] args) {
        boolean quit = false;
        Menu.printMenu();

        while (!quit) {
            System.out.print("\nEnter your choice: ");
            int choice;

            try {
                choice = Integer.parseInt(groceryList.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 0:
                    quit = true;
                    System.out.println("exited");
                    break;
                case 1:
                    groceryList.addItems();
                    break;
                case 2:
                    groceryList.removeItems();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

            groceryList.printSortedList();
        }
    }
}
