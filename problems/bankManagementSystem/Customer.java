package bankManagementSystem;

import java.util.ArrayList;

public class Customer {
	private String name;
	private ArrayList<Double> transactions;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Double> getTransactions() {
		return transactions;
	}
	public void setTransactions(ArrayList<Double> transactions) {
		this.transactions = transactions;
	}
	
	public Customer(String name,double transaction) {
		this.name=name;
		this.transactions=new ArrayList<>();
		if(transaction>=0) {
			transactions.add(transaction);
		}else {
			transactions.add(0d);
		}
		
	}
	
	public void addTransaction(double amount) {
		     double last=transactions.get(transactions.size()-1);
		     if(amount<0) {
		    	 if(last !=0  && amount<last) {
		    		 last=last-amount;
		    		 transactions.add(last);
		    	 }
		     }else {
		    	 last=last+amount;
		    	 transactions.add(last);
		     }
			
	}	
}
