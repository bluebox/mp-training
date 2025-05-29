package BankSystem;

import java.util.ArrayList;

public class Bank {
	private String name;
	private ArrayList<Branch> branches;
	
	public Bank(String name) {
		this.name=name;
		this.branches=new ArrayList<>();
	}
	
	public boolean addBranch(String name) {
		if(findBranch(name)==null) {
			branches.add(new Branch(name));
			System.out.println(name+" Branch added.");
			return true;
		}
		
		return false;
	}
	
	public boolean addcustomer(String branchName,String customerName,double iniTransaction) {
		Branch branch=findBranch(branchName);
		if(branch!=null) {
			branch.newCustomer(customerName, iniTransaction);
			System.out.println("Customer "+customerName+" added in branch "+branchName);

			return true;
		}
		System.out.println("branch Not found.");
		return false;
	}
	
	public boolean addCustomerTransaction(String branchName,String customerName,double transaction) {
		Branch branch=findBranch(branchName);
		if(branch!=null) {
			if(branch.findCustomer(customerName)!=null) {
				branch.addCustomerTransaction(customerName, transaction);
				System.out.println("Customer transaction added.");

				return true;
			}
		}
		System.out.println("customer Not found.");

		return false;
	}
	
	public Branch findBranch(String branchName) {
		for(Branch branch: branches) {
			if(branch.getName().equals(branchName)) {
				return branch;
			}
		}
		System.out.println("findbranch func Not found.");

		return null;
	}
	
	public boolean listCustomers(String branchName,boolean show) {
		Branch branch=findBranch(branchName);
		if(branch!=null) {
			System.out.println("Branch Name: "+branch.getName());
			ArrayList<Customer> customers=branch.getCustomers();
			for(int i=0;i<customers.size();i++) {
				Customer customersList=customers.get(i);
				System.out.println(customersList.getName());
				ArrayList<Double> transactions=customersList.getTransaction();
				System.out.println("Transactions List.");

				for(int j=0;j<transactions.size();j++) {
					System.out.println(transactions.get(j));

				}
			}
			
			return true;
		}
		System.out.println("Data Not found.");

		return false;
	}
}
