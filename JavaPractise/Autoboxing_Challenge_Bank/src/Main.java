import java.util.ArrayList;

class Customer {
    private String name;
    private ArrayList<Double> transactions;

    public Customer(String name) {
        this.name = name;
        this.transactions = new ArrayList<Double>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }
    public void addTransaction(double amount) {
        transactions.add(amount); 
    }
}

class Bank {
    private String name;
    private ArrayList<Customer> customers;

    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<Customer>();
    }

    public boolean addCustomer(String customerName) {
        if (findCustomer(customerName) == null) {
            customers.add(new Customer(customerName));
            System.out.println("Customer added: " + customerName);
            return true;
        }
        System.out.println("Customer already exists: " + customerName);
        return false;
    }

    public boolean addTransaction(String customerName, double amount) {
        Customer customer = findCustomer(customerName);
        if (customer != null) {
            customer.addTransaction(amount);
            System.out.println("Transaction added for " + customerName + ": " + amount);
            return true;
        }
        System.out.println("Customer not found: " + customerName);
        return false;
    }

    public void printStatement(String customerName) {
        Customer customer = findCustomer(customerName);
        if (customer != null) {
            System.out.println("Statement for " + customer.getName() + ":");
            ArrayList<Double> txns = customer.getTransactions();
            for (int i = 0; i < txns.size(); i++) {
                double value = txns.get(i);
                System.out.println("Transaction " + (i + 1) + ": " + value);
            }
        } 
        else {
            System.out.println("Customer not found: " + customerName);
        }
    }

    private Customer findCustomer(String customerName) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equalsIgnoreCase(customerName)) {
                return customers.get(i);
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Bank myBank = new Bank("My Bank");

        myBank.addCustomer("Akash");
        myBank.addTransaction("Akash", 500.0);
        myBank.addTransaction("Akash", -100.0);
        myBank.addTransaction("Akash", 200.5);

        myBank.printStatement("Akash");

        myBank.addCustomer("Akash");

        myBank.addCustomer("Pavan");
        myBank.printStatement("Pavan");
    }
}