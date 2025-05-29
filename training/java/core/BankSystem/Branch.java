package BankSystem;

import java.util.ArrayList;

public class Branch {
	private String branchName;
	private ArrayList<Customer> customers;

	public Branch(String branchName) {
		this.branchName = branchName;
		this.customers = new ArrayList<>();

	}

	public String getName() {
		return branchName;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	public boolean newCustomer(String customerName, double amount) {
		System.out.println("in branch class newcustomer "+customerName);
		if(findCustomer(customerName)==null) {
			customers.add(new Customer(customerName,amount));
			return true;
		}
	
		System.out.println("Customer added.");
		return false;		
	}
	
	public boolean addCustomerTransaction(String customerName,double transaction) {
		
		Customer check=findCustomer(customerName);
		if(check!=null) {
			check.addTransaction(transaction);
			return true;
		}
		return false;
	}
	public Customer findCustomer(String name) {
		
		for(Customer customer:customers) {
			if(customer.getName().equals(name)) {
				return customer;
			}
		}
		return null;
	}
//	public void allCustomers() {
//		String name = this.branchName;
//		System.out.println("Branch Name: " + name);
//		for (int i = 0; i < customers.size(); i++) {
//			Customer current = customers.get(i);
//			current.getName();
//			current.getTransaction();
//		}
//	}

}
