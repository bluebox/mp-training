package BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankCustomer {

    private final String name;
    private final int customer_Id;
    final List<BankAccount> accounts = new ArrayList<>();

    public BankCustomer(String name, int customer_Id) {
        this.name = name;
        this.customer_Id = customer_Id;
    }

    public int getCustomerId() {
        return customer_Id;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of customers to add: ");
        int n = sc.nextInt();
        sc.nextLine();

        List<BankCustomer> customers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Enter details for Customer " + (i + 1) + " ---");

            System.out.print("Enter customer name: ");
            String name = sc.nextLine();

            System.out.print("Enter customer id: ");
            int id = sc.nextInt();

            System.out.print("Enter customer balance: ");
            double balance = sc.nextDouble();

            sc.nextLine();

            BankAccount.AccountType accountType = null;
            while (accountType == null) {
                System.out.print("Enter customer account type (SAVINGS or CHECKING): ");
                String type = sc.nextLine().trim().toUpperCase();
                try {
                    accountType = BankAccount.AccountType.valueOf(type);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid account type. Please enter SAVINGS or CHECKING.");
                }
            }

            BankCustomer customer = new BankCustomer(name, id);
            BankAccount account = new BankAccount(balance, accountType);
            customer.accounts.add(account);

            customers.add(customer);
        }

        System.out.println("\n--- Customer Details ---");
        for (BankCustomer customer : customers) {
            BankAccount account = customer.accounts.get(0);
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Account Type: " + account.getAccountType());
            System.out.println("Account Balance: $" + account.getBalance());
            System.out.println("-----------------------------");
        }

        sc.close();
    }
}
