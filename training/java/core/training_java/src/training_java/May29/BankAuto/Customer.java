package BankAuto;

import java.util.ArrayList;

public class Customer {
	private String name;
	private ArrayList<Double> transactions;
	Customer(String name,double transaction){
		this.name=name;
		transactions=new ArrayList<Double>();
		transactions.add(transaction);
	}
	public String getName() {
		return name;
	}
	public ArrayList<Double> getTransactions(){
		return transactions;
	}
	
	public void addTransaction(double amount) {
		transactions.add(amount);	
	}
	

}
