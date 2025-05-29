package BankAuto;

import java.util.ArrayList;

public class Bank {
	private String name;
	private ArrayList<Branch> branches;
	Bank(String name){
		this.name=name;
		branches=new ArrayList<Branch>();
	}
	public boolean addBranch(String branchName) {
		if(findBranch(branchName)!=null) {
			return false;
		}
		branches.add(new Branch(branchName));
		return true;
	}
	
	private Branch findBranch(String branchName) {
		for (var branch :branches) {
			if(branch.getName().equals(branchName)) {
				return branch;
			}
		}
		return null;
	}
	
	public boolean addCustomer(String branchName,String customerName,double transaction) {
		Branch branch=findBranch(branchName);
		if(branch!=null) {
			branch.newCustomer(customerName,transaction);
			return true;
		}
		return false;	
		
	}
	
	public boolean addCustomerTransaction(String branchName,String customerName,double transaction) {
		Branch branch=findBranch(branchName);
		if(branch!=null) {
			return branch.addCustomerTransaction(customerName,transaction);
		}
		return false;	
		
	}
  public boolean listCustomers(String branchName,boolean flag) {
	  Branch branch=findBranch(branchName);
		if(branch!=null) {
			if(flag) {
				System.out.println(branch.getName());
				for(var customer:branch.getCustomers()) {
					System.out.println(customer.getName()+customer.getTransactions().toString());
				}
			}
			return true;
		}
		return false;	
  }
}
