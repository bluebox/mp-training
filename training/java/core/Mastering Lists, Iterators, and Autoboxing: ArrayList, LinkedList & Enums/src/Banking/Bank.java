package Banking;

import java.util.ArrayList;

public class Bank {
	String name;
	ArrayList<Branch> Branches;
	public Bank(String name)
	{
		this.name=name;
		Branches=new ArrayList<Branch>();
		
	}
	public boolean addBranch(String branch)
	{
		if(findBranch(branch)!=null)
		{
			Branches.add(new Branch(branch));
			return true;
		}
		return false;
	}
	public boolean addCustomer(String branchName,String customer,double transaction)
	{
		Branch branch=findBranch(branchName);
		if(branch!=null)
			return branch.newCustomer(customer,transaction);
		return false;
		
	}
	public boolean addCustomerTransaction(String branchName,String customer,double transaction)
	{
		Branch branch=findBranch(branchName);
		if(branch!=null)
			return branch.addCustomerTransaction(customer,transaction);
		return false;
		
	}
	private Branch findBranch(String branchName) {
		// TODO Auto-generated method stub
		for(int i=0;i<this.Branches.size();i++){
            Branch branch = this.Branches.get(i);
            if(branch.getName().equals(branchName)){
                return branch;
            }
        }
        return null;
		
	}
	public boolean listCustomers(String branchName,boolean printTransaction)
	{
		Branch branch=findBranch(branchName);
		if(branch!=null)
		{
			System.out.println("Customer details for Branch "+branchName);
			ArrayList<Customer> branchCustomers=branch.getCustomers();
			for(int i=0;i<branchCustomers.size();i++)
			{
				Customer branchCustomer = branchCustomers.get(i);
                System.out.println("Customer: "+branchCustomer.getName()+"["+(i+1)+"]");
                
                if(printTransaction){
                    System.out.println("Transactions");
                    ArrayList<Double> transactions = branchCustomer.getTransactions();
                    for(int j=0;j<transactions.size();j++){
                        System.out.println("["+(j+1)+"] Amount "+ transactions.get(j));
                    }
                }
			}
			return true;
		}
		return false;
	}
	
}
