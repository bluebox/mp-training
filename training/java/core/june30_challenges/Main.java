package june30_collections;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank("MyBank");

        boolean quit = false;
        while (!quit) {
            System.out.println("\n======== Banking Menu ========");
            System.out.println("1 - Add new customer");
            System.out.println("2 - Add transaction to customer");
            System.out.println("3 - Print customer statement");
            System.out.println("4 - Quit");
            System.out.print("Choose your option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter initial amount: ");
                    double initialAmount = Double.parseDouble(scanner.nextLine());
                    bank.addNewCustomer(name, initialAmount);
                    break;

                case 2:
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter transaction amount (positive for credit, negative for debit): ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    bank.addTransaction(customerName, amount);
                    break;

                case 3:
                    System.out.print("Enter customer name to print statement: ");
                    String custName = scanner.nextLine();
                    bank.printStatement(custName);
                    break;

                case 4:
                    quit = true;
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1-4.");
            }
        }

        scanner.close();
    }
}
