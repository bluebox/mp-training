package ds;

import java.util.ArrayList;
import java.util.List;


public class Customer {
	
	private String name;
	private ArrayList<Double> transactions; 
	
	public Customer(String name,double transaction) {
		
		this.name = name;
		this.transactions = new ArrayList<>(List.of(transaction));
	}
	
	public String getName() {
		
		return name;
	}
	
	public ArrayList<Double> getTransactions(){
		
		return transactions;
	}
	

}
