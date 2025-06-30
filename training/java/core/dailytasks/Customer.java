import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer {
    // Fields of the Customer class
    private String name;
    private double creditLimit;
    private String emailAddress;

    // 1. First Constructor: for all three fields
    // Assigns arguments directly to instance fields
    public Customer(String name, double creditLimit, String emailAddress) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.emailAddress = emailAddress;
        System.out.println("Customer created using all three fields constructor.");
    }

    
    public Customer() {
        
        this("Default Name", 1000.00, "default@example.com");
        System.out.println("Customer created using no-args constructor.");
    }

   
    public Customer(String name, String emailAddress) {
        // Calls the three-field constructor, setting a default credit limit
        this(name, 500.00, emailAddress);
        System.out.println("Customer created using name and email constructor.");
    }

    // Getter methods for each field (no setters are required as per challenge)
    public String getName() {
        return name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    // Helper method to display customer information
    public void printCustomerInfo() {
        System.out.println("\n--- Customer Information ---");
        System.out.println("Name: " + getName());
        System.out.printf("Credit Limit: $%.2f%n", getCreditLimit());
        System.out.println("Email: " + getEmailAddress());
        System.out.println("--------------------------");
    }


    // Main method to demonstrate the Customer class and its constructors with user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Demonstrating Customer Class Constructors ---");

        // --- Test Constructor 1: All three fields ---
        System.out.println("\n1. Creating a Customer using ALL THREE FIELDS (name, credit limit, email):");
        System.out.print("Enter Customer Name: ");
        String name1 = scanner.nextLine();

        double creditLimit1;
        while (true) {
            System.out.print("Enter Credit Limit (e.g., 2500.50): ");
            try {
                creditLimit1 = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value for credit limit.");
                scanner.next(); 
            }
        }
        scanner.nextLine(); 

        System.out.print("Enter Email Address: ");
        String email1 = scanner.nextLine();

        Customer customer1 = new Customer(name1, creditLimit1, email1);
        customer1.printCustomerInfo();


        
        System.out.println("\n2. Creating a Customer using NO ARGUMENTS (default values):");
        Customer customer2 = new Customer();
        customer2.printCustomerInfo();


        
        System.out.println("\n3. Creating a Customer using NAME AND EMAIL ONLY:");
        System.out.print("Enter Customer Name: ");
        String name3 = scanner.nextLine();

        System.out.print("Enter Email Address: ");
        String email3 = scanner.nextLine();

        Customer customer3 = new Customer(name3, email3);
        customer3.printCustomerInfo();

        scanner.close();
        System.out.println("\n--- Constructor Demonstration Complete ---");
    }
}