package day11.banking;

import java.util.ArrayList;

public class Branch {
private String name;
private ArrayList<Customer>customers;
Branch(String name){
	this.name=name;
}
public String getName() {
	return this.name;
}
public ArrayList<Customer> getCustomers(){
	return this.customers;
}

public  boolean newCustomer(String customerName,double initialTransaction) {
	
	if(findCustomer(customerName)!=null)
		return false;
	customers.add(new Customer(customerName,initialTransaction));
	return true;
}

public boolean addCustomerTransaction(String customerName,double transaction) {
	Customer c=findCustomer(customerName);
	if(c==null)
		return false;
	
	c.addTransaction(transaction);
	return true;
}
public Customer findCustomer(String customerName) {
	int index=customers.indexOf(customerName);
	if(index==-1)
		return null;
	return customers.get(index);
}
}
