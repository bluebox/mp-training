
import java.util.ArrayList;

public class Branch {
	
	private String name;
	private ArrayList<Customer> customers;
	
	public Branch(String name) {
		this.name = name;
		customers = new ArrayList<>();
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	private Customer findCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }
	
	public boolean newCustomer(String name, double initial) {
		if (findCustomer(name) == null) {
            customers.add(new Customer(name, initial));
            return true;
        }
        return false;
	}
	
	public boolean addCustomerTransaction(String name, double amount) {
		Customer temp=findCustomer(name);
		if(temp.getName() != null) {
			temp.addTransaction(amount);
			return true;
		}
		return false;
	}
}