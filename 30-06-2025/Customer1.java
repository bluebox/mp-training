import java.util.ArrayList;

public class Customer1 {
    private String name;
    private ArrayList<Double> transactions;

    public Customer1(String name, double initialTransaction) {
        this.name = name;
        this.transactions = new ArrayList<>();
        addTransaction(initialTransaction);
    }

    public String getName() {
        return name;
    }

    public void addTransaction(double amount) {
        transactions.add(amount); 
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }
}