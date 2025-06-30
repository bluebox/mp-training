import java.util.ArrayList;

public class Bank1 {
    private String name;
    private ArrayList<Customer> customers;

    public Bank1(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
    }

    public boolean addCustomer(String customerName, double initialTransaction) {
        if (findCustomer(customerName) == null) {
            customers.add(new Customer(customerName, initialTransaction));
            System.out.println("Customer " + customerName + " added with initial transaction: " + initialTransaction);
            return true;
        }
        System.out.println("Customer " + customerName + " already exists.");
        return false;
    }

    public boolean addTransaction(String customerName, double amount) {
        Customer existing = findCustomer(customerName);
        if (existing != null) {
            existing.addTransaction(amount);
            System.out.println("Added transaction of " + amount + " to " + customerName);
            return true;
        }
        System.out.println("Customer " + customerName + " not found.");
        return false;
    }

    public void printCustomerStatement(String customerName) {
        Customer customer = findCustomer(customerName);
        if (customer != null) {
            System.out.println("Statement for " + customer.getName() + ":");
            int i = 1;
            for (Double transaction : customer.getTransactions()) {
                
                double amt = transaction;
                System.out.println("  Transaction " + i++ + ": " + amt);
            }
        } else {
            System.out.println("Customer " + customerName + " not found.");
        }
    }

    private Customer findCustomer(String name) {
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
}