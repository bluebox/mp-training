package corejava.june30_ArrayList;

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

	    public boolean newCustomer(String customerName, double initialTransaction) {
	        if (findCustomer(customerName) == null) {
	            customers.add(new Customer(customerName, initialTransaction));
	            return true;
	        }
	        return false;
	    }

	    public boolean addCustomerTransaction(String customerName, double transaction) {
	        Customer existingCustomer = findCustomer(customerName);
	        if (existingCustomer != null) {
	            existingCustomer.addTransaction(transaction);
	            return true;
	        }
	        return false;
	    }

	    public Customer findCustomer(String customerName) {
	        for (Customer customer : customers) {
	            if (customer.getName().equalsIgnoreCase(customerName)) {
	                return customer;
	            }
	        }
	        return null;
	    }
}
