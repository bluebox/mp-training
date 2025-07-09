package day4;

import java.util.ArrayList;

public class Customer {
	private String branch, name;

	public String getBranch() {
		return branch;
	}

	private ArrayList<Double> transactions;

	public Customer(String branch, String name) {
		super();
		this.branch = branch;
		this.name = name;
		transactions = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<Double> getTransactions() {
		return transactions;
	}

	public void addTransacation(Double amount) {
		transactions.add(amount);
	}
}