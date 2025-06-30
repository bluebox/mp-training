import java.util.ArrayList;
public class Branch {
	private String name;
	private ArrayList<Customer> customers;
	
	public Branch(String name) {
		this .name=name;
		this.customers=new ArrayList<>();
		
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public boolean newCustomer(String name,double initialTransaction) {
		if(findCustomer(name)!=null)
			return true;
		return false;
	}
	
	public boolean addTransaction(String customerName, double amount) {
        Customer existingCustomer = findCustomer(customerName);
        if (existingCustomer != null) {
            existingCustomer.addTransaction(amount);
            return true;
        }
        return false;
    }
    
    private Customer findCustomer(String customerName) {
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(customerName)) {
                return c;
            }
        }
        return null;
    }
	
	
}
