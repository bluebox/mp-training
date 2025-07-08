import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskData taskData = new TaskData();

        System.out.println("--- Set Operations Challenge - Task Management ---");

        boolean running = true;
        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. View tasks for a specific assignee (or 'all')");
            System.out.println("2. Perform Set Operations (Union/Intersection)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter assignee name (Manager, Ann, Bob, Carol, or All): ");
                    String assigneeName = scanner.nextLine();
                    Set<Task> tasks = taskData.getTasks(assigneeName);
                    System.out.println("\nTasks for " + assigneeName + ":");
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks found or invalid assignee name.");
                    } else {
                        tasks.forEach(System.out::println);
                    }
                    break;

                case 2:
                    performSetOperations(scanner, taskData);
                    break;

                case 3:
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void performSetOperations(Scanner scanner, TaskData taskData) {
        System.out.println("\n--- Perform Set Operations ---");
        System.out.println("Available assignees: Manager, Ann, Bob, Carol");

        System.out.print("Enter name for Set A: ");
        String nameA = scanner.nextLine();
        Set<Task> setA = taskData.getTasks(nameA);

        System.out.print("Enter name for Set B: ");
        String nameB = scanner.nextLine();
        Set<Task> setB = taskData.getTasks(nameB);

        if (setA.isEmpty() && !nameA.equalsIgnoreCase("all") || setB.isEmpty() && !nameB.equalsIgnoreCase("all")) {
             System.out.println("One or both assignee names are invalid or have no tasks. Cannot perform operation.");
             return;
        }

        System.out.println("\nSet A (" + nameA + "):");
        setA.forEach(System.out::println);
        System.out.println("\nSet B (" + nameB + "):");
        setB.forEach(System.out::println);

        Set<Task> union = new TreeSet<>(setA);
        union.addAll(setB);
        System.out.println("\n--- Union (Tasks for " + nameA + " OR " + nameB + ") ---");
        union.forEach(System.out::println);

        Set<Task> intersection = new TreeSet<>(setA);
        intersection.retainAll(setB);
        System.out.println("\n--- Intersection (Tasks for " + nameA + " AND " + nameB + ") ---");
        intersection.forEach(System.out::println);

        Set<Task> differenceAminusB = new TreeSet<>(setA);
        differenceAminusB.removeAll(setB);
        System.out.println("\n--- Difference (Tasks for " + nameA + " BUT NOT " + nameB + ") ---");
        differenceAminusB.forEach(System.out::println);

        Set<Task> differenceBminusA = new TreeSet<>(setB);
        differenceBminusA.removeAll(setA);
        System.out.println("\n--- Difference (Tasks for " + nameB + " BUT NOT " + nameA + ") ---");
        differenceBminusA.forEach(System.out::println);
    }
}