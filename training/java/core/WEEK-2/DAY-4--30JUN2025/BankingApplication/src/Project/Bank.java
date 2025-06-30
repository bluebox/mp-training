package Project;
import java.util.ArrayList;

public class Bank {
	private String name;
	private ArrayList<Branch> branches;
	
	public Bank(String name) {
		this.name=name;
		this.branches=new ArrayList<Branch>();
	}
	
	public boolean addBranch(String branchName) {
		try {
			Branch b=new Branch(branchName);
			this.branches.add(b);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public boolean addCustomer(String branchName, String customerName, double initialTransaction) {
		try {
			Branch b=this.findBranch(branchName);
			b.newCustomer(customerName, initialTransaction);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {
		try {
			Branch b=this.findBranch(branchName);
			Customer c=b.findCustomer(customerName);
			c.addTransaction(transaction);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public Branch findBranch(String branchName) {
		for(Branch b:this.branches) {
			if(b.getName().equals(branchName)) {
				return b;
			}
		}
		return null;
	}
	
	public boolean listCustomers(String branchName, boolean printTransaction) {
		try {
			Branch b=this.findBranch(branchName);
			b.printCustomers(printTransaction);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
