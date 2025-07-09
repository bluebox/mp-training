package day4;

import java.util.ArrayList;

public class Bank {
	private String name;
	private ArrayList<String> branches;
	private ArrayList<Customer> customersList;

	public Bank(String name) {
		super();
		this.name = name;
		branches = new ArrayList<>();
		customersList = new ArrayList<>();
	}

	public void addBranch(String branch) {
		if (!branches.contains(branch)) {
			branches.add(branch);
		}
	}

	public void addCustomerTransaction(String branch, String name, double amount) {
		for (Customer customer : customersList) {
			if (customer.getName().equalsIgnoreCase(name) && customer.getBranch().equalsIgnoreCase(branch)) {
				customer.addTransacation(amount);
				return;
			}
		}
		Customer newCustomer = new Customer(branch, name);
		newCustomer.addTransacation(amount);
	}

	public void listCustomers(String branch, boolean printTransactions) {
		for (int i = 0; i < customersList.size(); i++) {
			Customer customer = customersList.get(i);
			System.out.printf("Customer: %s[%d]\n", customer.getName(), (i + 1));
			if (printTransactions) {
				System.out.println("Transacations");
				ArrayList<Double> list = customer.getTransactions();
				for (int j = 0; j < list.size(); j++) {
					System.out.printf("[%d] Amount %f\n", j + 1, list.get(j));
				}
			}
		}
	}

	public void addCustomer(String branch, String name, double amount) {
		Customer newCustomer = new Customer(branch, name);
		newCustomer.addTransacation(amount);
		customersList.add(newCustomer);
	}
}
