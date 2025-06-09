package ds;

import java.util.ArrayList;


public class Customer {
	
	private String name;
	private ArrayList<Double> transactions; 
	
	public Customer(String name,double transaction) {
		
		this.name = name;
		this.transactions = new ArrayList<>();
		addTransaction(transaction);
	}
	
	public String getName() {
		
		return name;
	}
	
	public ArrayList<Double> getTransactions(){
		
		return transactions;
	}
	
	public void addTransaction(double transaction) {
		
		transactions.add(transaction);
		if(transaction < 0) {
			
			System.out.println("Transaction successful , Money Debited");
		}
		else {
			System.out.println("Transaction successful , Money Credited");
		}
	}

}
