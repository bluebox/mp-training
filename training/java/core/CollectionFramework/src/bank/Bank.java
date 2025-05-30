package bank;

import java.util.ArrayList;

public class Bank {
	
	
	private String name;
	private ArrayList<Branch> branches;
	
	private class Branch{
		
		private String branchName;
		private ArrayList<Branch.Customers> customers;
		
		public Branch(String branchName)
		{
			customers = new ArrayList<Bank.Branch.Customers>();
			this.branchName = branchName;
		}
		
		
		private class Customers
		{
			private String name;
			private ArrayList<Double> transactions;
			
			
			
			public Customers(String name)
			{
				this.name = name;
				transactions = new ArrayList<Double>();
				customers.add(this);
			}
			
			public String getName()
			{
				return this.name;
			}
			
			public void getTransactions()
			{
				System.out.println(this.name+"'s transaction details");
				int i = 0;
				for(var transaction: transactions)
				{
					System.out.println(""+(i+1)+") "+transaction);
				}
			}
			public boolean addTransaction(Double transaction)
			{
				transactions.add(transaction);
				return true;
			}
		}
		
		
		public void getCustomers()
		{
			System.out.println("Customers in "+branchName+": ");
			int i = 0;
			for(var cust:customers)
			{
				System.out.println(""+i+") "+cust.getName());
				i+=1;
			}
		}
		
		public String getBranchName()
		{
			return this.branchName;
		}
		
		public boolean createCustomer(String name)
		{
			for(var cust:customers)
			{
				if(cust.getName().equals(name))
				{
					System.out.println("Customer wiht name :: "+name+" already exist.");
					return false;
				}
			}
			new Customers(name);
			return true;
		}
		
		public boolean getTransactionDetails(String custName)
		{
			boolean isPresent = false;
			
			for(var cust:customers)
			{
				if(cust.getName().equals(custName))
				{
					cust.getTransactions();
					isPresent = true;
				}
			}
			return isPresent;
		}
		
	}
	
	
	
	public boolean makeTransaction(String custName, String branchName ,Double amount)
	{
		boolean isComplete = false;
		for(var branch: branches)
		{
			if(branch.getBranchName().equals(branchName))
			{
				for(var cust:branch.customers)
				{
					if(cust.getName().equals(custName))
					{
						cust.addTransaction(amount);
						isComplete = true;
					}else
					{
						System.out.println("Customer "+custName+" is not found in "+branchName);
					}
				}
			}else
			{
				System.out.println("Branch "+branchName+" is not found in "+name);
			}
		}
		return isComplete;
		
		
	}
	
	public boolean addBranch(String branchName)
	{
		for(var branch:branches)
		{
			if(branch.getBranchName().equals(branchName))
			{
				System.out.println("Branch with "+branchName+" already exist");
				return false;
			}
		}
		branches.add(new Branch(branchName));
		return true;
	}
	
	
	public boolean addCustomer(String custName,String branchName)
	{
		for(var branch:branches)
		{
			if(branch.getBranchName().equals(branchName))
			{
				
				return branch.createCustomer(custName);
			}else
			{
				System.out.println(branch.getBranchName()+" branch not found");
				return false;
			}
		}
		return true;
	}
	
	
	
	public boolean getCustomerTrans(String custName, String branchName)
	{
		for(var branch:branches)
		{
			if(branch.getBranchName().equals(branchName))
			{
				branch.getTransactionDetails(custName);
			}else
			{
				System.out.println(branch.getBranchName()+" branch not found");
				return false;
			}
		}
		return false;
	}
	
	
	public void getbranchCust(String branchName)
	{
		for(var branch:branches)
		{
			if(branch.getBranchName().equals(branchName))
			{
				branch.getCustomers();
			}
		}
	}
	
	public Bank(String bankName,String bankBranch)
	{
		
		this.name = bankName;
		if(branches== null)
		{
			branches = new ArrayList<Bank.Branch>();
			branches.add(new Branch(bankBranch));
		}
		else {
			for(var branch:branches)
			{
				if(branch.getBranchName().equals(bankBranch))
				{
					
				}else
				{
					branches.add(new Branch(bankBranch));
				}
			}
		}
		
		
	}
	@Override
	public String toString()
	{
		return name+" has the following branches"+branches;
	}
}
