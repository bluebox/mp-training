package ds;

import java.util.ArrayList;

public class Bank {
	
	private String name;
	private ArrayList<Branch> branches;
	
	public Bank(String name) {
		
		this.name = name;
		branches = new ArrayList<>();
	}
	
	public boolean addBranch(String name) {
		if(findBranch(name) != null) 
		{
			branches.add(new Branch(name));
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean addCustomer(String branchName, String customerName, double transaction) {
		
		
		return false;
	}

	private Branch findBranch(String name) {
		
		return null;
	}
}
