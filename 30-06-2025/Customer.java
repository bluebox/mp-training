import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double> transactions;

    public Customer(String name, double initialTransaction) {
        this.name = name;
        this.transactions = new ArrayList<>();
        addTransaction(initialTransaction);
    }

    public String getName() {
        return name;
    }

    public void addTransaction(double amount) {
        transactions.add(amount); // autoboxing
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }
}