
import java.util.ArrayList;
public class Bank {
	private String name;
	private ArrayList<Branch> branches;
	
	public Bank(String name) {
		this.name = name;
		branches = new ArrayList<>();
	}
	
	private Branch findBranch(String name) {
		for(Branch branch : branches) {
			if(branch.getName().equals(name)) {
				return branch;
			}
		}
		return null;
	}
	public boolean addBranch(String name) {
		if(findBranch(name) == null) {
			branches.add(new Branch(name));
			return true;
		}
		return false;
	}
	public boolean addCustomer(String bname, String cname, double initial) {
		Branch temp = findBranch(bname);
		if(temp != null) {
			return temp.newCustomer(cname, initial);
		}
		return false;
	}
	public boolean addCustomerTransaction(String bname, String cname, double initial) {
		Branch temp = findBranch(bname);
		if(temp != null) {
			return temp.addCustomerTransaction(cname, initial);
		}
		return false;
	}
	public boolean listCustomers(String name, boolean printTransactions) {
		
		Branch temp = findBranch(name);
		if(temp == null) return false;
		
		ArrayList<Customer> customers = temp.getCustomers();
		System.out.println("Customer details for branch " + name);
		for(int i=0;i<customers.size();i++) {
			Customer customer = customers.get(i);
			System.out.println("Customer: " + customer.getName() + "[" + (i+1) + "]");
			
			if(printTransactions) {
				System.out.println("Transactions");
				ArrayList<Double> transaction = customer.getTransactions();
				for(int j=0; j<transaction.size(); j++) {
					System.out.println("[" + (j+1) + "] Amount " + transaction.get(j));
				}
			}
		}
		return true;
	}
}