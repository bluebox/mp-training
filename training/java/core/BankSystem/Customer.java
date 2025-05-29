package BankSystem;

import java.util.ArrayList;

public class Customer {
	private String customerName;
	private ArrayList<Double> transactions;
	
	public Customer(String customerName,double transaction) {
		this.customerName=customerName;
		this.transactions=new ArrayList<>();
		addTransaction(transaction);
	}
	
	public String getName() {
		String name=this.customerName;
//		System.out.println(name);
		return name;
	}
	public ArrayList<Double> getTransaction() {
		return transactions;
	}
	public void addTransaction(double amount) {
		transactions.add(amount);
		System.out.println("transaction added");
	}
	
}
