package june30_collections;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Customer> customers;

    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
    }

    public boolean addNewCustomer(String customerName, double initialAmount) {
        if (findCustomer(customerName) == null) {
            customers.add(new Customer(customerName, initialAmount));
            System.out.println("Customer " + customerName + " added with initial amount " + initialAmount);
            return true;
        }
        System.out.println("Customer " + customerName + " already exists.");
        return false;
    }

    public boolean addTransaction(String customerName, double amount) {
        Customer existingCustomer = findCustomer(customerName);
        if (existingCustomer != null) {
            existingCustomer.addTransaction(amount);
            System.out.println("Transaction of " + amount + " added for " + customerName);
            return true;
        }
        System.out.println("Customer " + customerName + " not found.");
        return false;
    }

    public void printStatement(String customerName) {
        Customer existingCustomer = findCustomer(customerName);
        if (existingCustomer != null) {
            System.out.println("\nTransaction statement for " + existingCustomer.getName() + ":");
            ArrayList<Double> transactions = existingCustomer.getTransactions();
            for (int i = 0; i < transactions.size(); i++) {
                double amount = transactions.get(i);  // Unboxing
                String type = amount >= 0 ? "Credit" : "Debit";
                System.out.println((i + 1) + ". " + type + ": " + amount);
            }
        } else {
            System.out.println("Customer " + customerName + " not found.");
        }
    }

    private Customer findCustomer(String customerName) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                return customer;
            }
        }
        return null;
    }
}
