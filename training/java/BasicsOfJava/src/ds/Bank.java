package ds;

import java.util.ArrayList;


public class Bank {
	
	private String name;
	private ArrayList<Branch> branches;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setBranches(ArrayList<Branch> branches) {
		this.branches = branches;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Branch> getBranches() {
		return branches;
	}

	public Bank(String name) {
		
		this.name = name;
		branches = new ArrayList<>();
	}
	
	public boolean addBranch(String name) {
		if(findBranch(name) == null) 
		{
			branches.add(new Branch(name));
			return true;
		}
		else 
		{
			System.out.println(" Branch already exists ");
			return false;
		}
	}
	
	public boolean addCustomer(String branchName, String customerName, double transaction) {
		
		Branch branch = findBranch(branchName);
		if(branch != null) {
			branch.newCustomer(customerName, transaction);
			return branch.addCustomerTransaction(customerName, transaction);
		}
		System.out.println(" Branch not exists ");
		return false;
	}
	
	public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {
		
		Branch branch = findBranch(branchName);
		if(branch != null) {
			
			return branch.addCustomerTransaction(customerName, transaction);
			
		}
		System.out.println(" Branch not exists ");
		return false;
	}

	public Branch findBranch(String name) {
		
		for(Branch branch : branches) {
			
			if(branch.getName().equalsIgnoreCase(name)) {
				return branch;
			}
		}
		return null;
	}
	
	public boolean listCustomers(String name,boolean printTransactions) {
		
		Branch branch = findBranch(name);
		if(branch != null) {
			
			ArrayList<Customer> customers= branch.getCustomers();
			System.out.println(" Bank Name " + this.name);
			if(printTransactions) {
				System.out.println(customers);
				
				for(Customer customer : customers) {
					
					ArrayList<Double> transactions = customer.getTransactions();
					int i = 0;
					System.out.println(" Customer name " + customer.getName() + "\n Customer transactions");
//					System.out.println(transactions);
					for(double transaction : transactions) {
						
						System.out.println("\t transaction #" + i + " " + transaction);
						i += 1;
					}
				}
			}
			else {
				int i = 0;
				for(Customer customer : customers) {
					
					System.out.println("Customer #" + i + " name: " + customer.getName());
					i += 1;
				}
			}
			
			
			return true;
		}
		return false;
	}
}
