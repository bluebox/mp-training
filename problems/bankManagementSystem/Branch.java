package bankManagementSystem;
import java.util.*;


public class Branch {
   private String name;
   private ArrayList<Customer> customers;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	public Branch(String name) {
		this.name=name;
		customers=new ArrayList<>();
	}
	
	public boolean newCustomer(String name,double amount) {
		Customer newCustomer=new Customer(name,amount);
		if(customers.contains(newCustomer)) {
			return false;
		}else {
			customers.add(newCustomer);
		}
		
		return true;
	}
	
	public boolean addCustomerTransaction(String name,double amount) {
		ListIterator<Customer> iterator=customers.listIterator();
		boolean res=false;
		while(iterator.hasNext()) {
			if(iterator.next().getName().equals(name)) {
				Customer customer=iterator.next();
				System.out.println("The customer added");
				customer.addTransaction(amount);
				res=true;
			}
		}
		return res;
	}
	
	public Customer findCustomer(String name) {
		ListIterator<Customer> iterator=customers.listIterator();
		Customer customer=null;
		while(iterator.hasNext()) {
			if(iterator.next().getName().equals(name)) {
				 customer=iterator.next();
				 System.out.print("Customer found successfully");
			}
		}
		return customer;
		
	}
    
    
    
}
