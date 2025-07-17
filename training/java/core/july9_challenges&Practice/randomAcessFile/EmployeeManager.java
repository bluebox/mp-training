package randomAcessFile;


import java.io.*;
import java.util.*;

public class EmployeeManager {
    private final String txtFile = "employees.txt";
    private final String datFile = "employees.dat";
    private EmployeeService service;

    public void start() {
        try {
            Map<Integer, Long> indexMap = EmployeeDataWriter.convertTxtToBinary(txtFile, datFile);
            service = new EmployeeService(datFile, indexMap);

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                printMenu();
                choice = scanner.nextInt();
                handleChoice(choice, scanner);
            } while (choice != 4);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printMenu() {
        System.out.println("\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
        System.out.println("1. List all Employee IDs");
        System.out.println("2. View Employee Details");
        System.out.println("3. Update Employee Salary");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private void handleChoice(int choice, Scanner scanner) throws IOException {
        switch (choice) {
            case 1 -> service.listEmployeeIds();

            case 2 -> {
                System.out.print("Enter Employee ID to view: ");
                int viewId = scanner.nextInt();
                service.displayEmployee(viewId);
            }

            case 3 -> {
                System.out.print("Enter Employee ID to update: ");
                int updateId = scanner.nextInt();

                double currentSalary = service.getSalary(updateId);
                if (currentSalary == -1) {
                    System.out.println("Employee not found!");
                    return;
                }

                System.out.print("Enter increment amount: ");
                double increment = scanner.nextDouble();
                double newSalary = currentSalary + increment;

               
                service.updateSalary(updateId, newSalary);
                System.out.println("✅ Salary updated successfully!");
                service.displayEmployee(updateId);
            }

            case 4 -> System.out.println("-----Exiting---");

            default -> System.out.println("Invalid option. Try again.");
        }
    }
}
