package corejava.june30_ArrayList;

import java.util.ArrayList;

public class Bank {
	    private String name;
	    private ArrayList<Branch> branches;

	    public Bank(String name) {
	        this.name=name;
	        this.branches = new ArrayList<>();
	    }
	    
	    public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}
		
	    public boolean addBranch(String branchName) {
	        if (findBranch(branchName) == null) {
	            branches.add(new Branch(branchName));
	            return true;
	        }
	        return false;
	    }
	    
	    public Branch findBranch(String branchName) {
	        for (Branch branch : branches) {
	            if (branch.getName().equalsIgnoreCase(branchName)) {
	                return branch;
	            }
	        }
	        return null;
	    }

	    
	    public boolean addCustomer(String branchName, String customerName, double initialTransaction) {
	        Branch branch = findBranch(branchName);
	        if (branch != null) {
	            return branch.newCustomer(customerName, initialTransaction);
	        }
	        return false;
	    }

	    public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {
	        Branch branch = findBranch(branchName);
	        if (branch != null) {
	            return branch.addCustomerTransaction(customerName, transaction);
	        }
	        return false;
	    }

	    

	    public boolean listCustomers(String branchName, boolean printTransactions) {
	        Branch branch = findBranch(branchName);
	        if (branch != null) {
	            System.out.println("Customers of branch: " + branch.getName());
	            ArrayList<Customer> branchCustomers = branch.getCustomers();
	            int customerId = 1;
	            for (Customer customer : branchCustomers) {
	                System.out.println(+customerId+". Customer: " + customer.getName());
	                if (printTransactions) {
	                    System.out.println("\tTransactions:");
	                    ArrayList<Double> transactions = customer.getTransactions();
	                    int transactionId = 1;
	                    for (Double transaction : transactions) {
	                        System.out.println(" \t[" + transactionId+ "] Amount " + transaction);
	                        transactionId++;
	                    }
	                }
	                customerId++;
	            }
	            return true;
	        }
	        return false;
	    }
}
