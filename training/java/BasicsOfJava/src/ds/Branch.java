package ds;

import java.util.ArrayList;

public class Branch {

	private String name;
	private ArrayList<Customer> customers;
	
	public Branch(String name) {
		this.name = name;
		this.customers = new ArrayList<>();
	}
	
	public String getName() {
		
		return name;
	}
	
	public ArrayList<Customer> getCustomers() {
		
		return customers;
	}
	
	public boolean newCustomer(String customerName, double transaction) {
		
		Customer customer = findCustomer(customerName);
		
		if(customer == null) {
			
			customers.add(new Customer(customerName, transaction));
			return true;
		}
		System.out.println("Customer already exists  ");
		return false;
	}
	
	public boolean addCustomerTransaction(String customerName,double transaction) {
		
		Customer customer = findCustomer(customerName);
		
		if(customer != null) {
			customer.addTransaction(transaction);
			return true;
		}
		return false;
	}
	
	public Customer findCustomer(String customerName) {
		
		for(Customer customer : customers) {
			
			if(customer.getName().equalsIgnoreCase(name)) {
				
				return customer;
			}
		}
		return null;
		
	}
}
