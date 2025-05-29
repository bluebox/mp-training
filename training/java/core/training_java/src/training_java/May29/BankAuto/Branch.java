package BankAuto;

import java.util.ArrayList;

public class Branch {
	private String branchName;
	private ArrayList<Customer> customers;
	public Branch(String branchName){
		this.branchName=branchName;
		customers=new ArrayList<Customer>();
	}
	public String getName() {
		return this.branchName;
	}
	public ArrayList<Customer> getCustomers(){
		return customers;
	}
	
	public boolean addCustomerTransaction(String customerName,double transaction) {
		Customer customer=findCustomer(customerName);
		if(customer!=null) {
			customer.addTransaction(transaction);
			return true;
		}
		return false;
	}
	
	public boolean newCustomer(String customerName,double transaction ) {
		if(findCustomer(customerName)!=null) {
			return false;
		}
		customers.add(new Customer(customerName,transaction));
		return true;
	}
	
	private Customer findCustomer(String customerName) {
		for(var customer:customers) {
			if(customer.getName().equals(customerName)) {
				return customer;
			}
		}
		return null;
	}

}
