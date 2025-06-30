import java.util.InputMismatchException;
import java.util.Scanner;

public class ClassesChallenge { // New project called ClassesChallenge with the usual Main class

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Welcome to Bank Account Management ---");

        // Create an instance of an Account class
        // Let's create an account using the default constructor first, then set details.
        Account myAccount = new Account();

        System.out.println("\nLet's set up your new account details:");

        System.out.print("Enter Account Number: ");
        myAccount.setAccountNumber(scanner.nextLine());

        System.out.print("Enter Customer Name: ");
        myAccount.setCustomerName(scanner.nextLine());

        System.out.print("Enter Email Address: ");
        myAccount.setEmail(scanner.nextLine());

        System.out.print("Enter Phone Number: ");
        myAccount.setPhoneNumber(scanner.nextLine());

        double initialBalance = 0.0;
        while (true) {
            System.out.print("Enter Initial Balance ($): ");
            try {
                initialBalance = scanner.nextDouble();
                if (initialBalance < 0) {
                    System.out.println("Initial balance cannot be negative. Please enter a positive value.");
                } else {
                    myAccount.setBalance(initialBalance);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value for balance.");
                scanner.next(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the remaining newline character after nextDouble()

        myAccount.printAccountInfo(); // Print information to the console

        // Test deposit and withdraw methods
        System.out.println("\n--- Testing Account Operations ---");

        double amount;

        // Deposit funds
        while (true) {
            System.out.print("Enter amount to deposit ($): ");
            try {
                amount = scanner.nextDouble();
                myAccount.deposit(amount);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value for deposit.");
                scanner.next();
            }
        }
        scanner.nextLine(); // Consume newline

        // Print balance after deposit
        System.out.printf("Balance after deposit: $%.2f%n", myAccount.getBalance());

        // Withdraw funds
        while (true) {
            System.out.print("Enter amount to withdraw ($): ");
            try {
                amount = scanner.nextDouble();
                myAccount.withdraw(amount); // Test your withdraw methods
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value for withdrawal.");
                scanner.next();
            }
        }
        scanner.nextLine(); // Consume newline

        // Print balance after withdrawal
        System.out.printf("Balance after withdrawal: $%.2f%n", myAccount.getBalance());

        // Test another withdrawal that makes balance negative (should be prevented)
        System.out.println("\nAttempting to withdraw more than current balance...");
        myAccount.withdraw(myAccount.getBalance() + 100); // Try to withdraw more than available
        System.out.printf("Balance after attempted overdraft: $%.2f%n", myAccount.getBalance());

        scanner.close();
        System.out.println("\n--- End of Bank Account Simulation ---");
    }
}
public class Account {
    // Fields (characteristics) of a bank account
    private String accountNumber;
    private double balance;
    private String customerName;
    private String email;
    private String phoneNumber;

    // Constructor to initialize an Account object (optional, but good practice)
    public Account(String accountNumber, double balance, String customerName, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Default constructor (no arguments)
    public Account() {
        // You can set default values here if needed, or leave empty
        this("00000000", 0.00, "Default Name", "default@example.com", "000-000-0000");
    }

    // Getters and Setters for each field
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Method to deposit funds
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.printf("Deposit of $%.2f successful. New balance: $%.2f%n", amount, this.balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw funds
    public void withdraw(double amount) {
        // A customer should not be allowed to withdraw funds if that withdrawal takes their balance negative.
        if (amount > 0 && this.balance - amount >= 0) {
            this.balance -= amount;
            System.out.printf("Withdrawal of $%.2f successful. New balance: $%.2f%n", amount, this.balance);
        } else if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else {
            System.out.printf("Insufficient funds. Current balance: $%.2f. Cannot withdraw $%.2f%n", this.balance, amount);
        }
    }

    // Method to print account information
    public void printAccountInfo() {
        System.out.println("--- Account Information ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.printf("Balance: $%.2f%n", balance);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("---------------------------");
    }
}
