package day4;

import java.util.ArrayList;

public class Branch {
	private String name;
	private ArrayList<Customer> customers;

	public Branch(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public Customer findCustomer(String name) {
		for (Customer customer : customers) {
			if (customer.getName().equalsIgnoreCase(name)) {
				return customer;
			}
		}
		return null;
	}

	public boolean addCustomerTranscation(String branch, String name, Double amount) {
		for (Customer customer : customers) {
			if (customer.getName().equalsIgnoreCase(name)) {
				customer.addTransacation(amount);
				return true;
			}
		}
		return false;
	}

	public boolean newCustomer(String branch, String name, Double amount) {
		for (Customer customer : customers) {
			if (customer.getName().equalsIgnoreCase(name)) {
				customer.addTransacation(amount);
				return false;
			}
		}
		Customer newCustomer = new Customer(branch, name);
		newCustomer.addTransacation(amount);
		return true;
	}

}
