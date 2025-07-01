package corejava.june30_ArrayList;

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

	    public ArrayList<Double> getTransactions() {
	        return transactions;
	    }

	    public void addTransaction(double transaction) {
	        transactions.add(transaction);
	    }
}
