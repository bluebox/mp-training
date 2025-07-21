package Project;
import java.util.ArrayList;

public class Branch {
	private String name;
	private ArrayList<Customer> customers;
	
	public Branch(String name) {
		this.name=name;
		this.customers=new ArrayList<Customer>();
	}
	
	public Branch() {
		this("");
	}

	public String getName() {
		return name;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	public boolean newCustomer(String customerName, double initialTransaction) {
		try {
			Customer customer=new Customer(customerName, initialTransaction);
			this.customers.add(customer);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public boolean addCustomerTransaction(String customerName, double transaction) {
		try {
			this.findCustomer(customerName).addTransaction(transaction);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public Customer findCustomer(String customerName) {
		Customer current=new Customer();
		for(Customer c:this.customers) {
			if(c.getName()==customerName) {
				current=c;
			}
		}
		return current;
	}
	
	public void printCustomers(boolean printTransaction) {
		for(Customer c:this.customers) {
			c.printCustomer(printTransaction);
		}
	}
}
