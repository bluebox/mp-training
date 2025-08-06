package bankingChallenge;

import java.util.ArrayList;

public class Bank {
	private String name;
	private ArrayList<Customer> customers = new ArrayList<>();
	
	public Bank(String name) {
		this.name=name;
	}
	
	private Customer getCustomer(String customerName) {
		
		for(Customer customer : customers) {
			if(customer.getName().equalsIgnoreCase(customerName)) {
				return customer;
			}
		}
		
		return null;
	}
	
	public void addNewCustomer(String customerName, double transaction) {
		if(getCustomer(customerName) == null) {
			Customer customer = new Customer(customerName, transaction);
			customers.add(customer);
			System.out.println("New Customer added: "+ customer);
		}
	}
	
	
	public void addTransaction(String name, double transactionAmount) {
		Customer customer = getCustomer(name);
		
		if(customer != null) {
			customer.getTransactions().add(transactionAmount);
			System.out.println("Transaction successfull");
		}
		else {
			System.out.println("Customer "+name + " wasn't found ");
		}
	}
	
	public void printTransactions(String customerName) {
		Customer customer = getCustomer(customerName);
		
		if(customer == null) {
			System.out.println("Customer "+customerName + " wasn't found ");
			return;
		}
		System.out.println("bank is "+ name);
		System.out.println("Customer Name: "+customer.getName());
		System.out.println("Transactions:");
		
		for(double d : customer.getTransactions()) {
			System.out.printf("%.2f  %s%n",d,d<0 ? "debit":"credit");
		}
		
	}
}
