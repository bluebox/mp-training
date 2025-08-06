package bankingChallenge;

import java.util.ArrayList;

public class Customer {
	private String name;
	private ArrayList<Double> transactions;
	
	public Customer(String name, Double transactions) {
		
		this.name = name.toUpperCase();
		this.transactions = new ArrayList<>();
		this.transactions.add(transactions);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Double> getTransactions() {
		return transactions;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", transactions=" + transactions + "]";
	}
	
}
