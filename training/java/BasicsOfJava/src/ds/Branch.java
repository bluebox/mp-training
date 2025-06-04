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
		
		
		return false;
	}
}
