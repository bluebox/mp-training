package bankManagementSystem;
import java.util.*;

public class Bank {
    
	private String name;
	private ArrayList<Branch> branches;
	
	
	public Bank(String name) {
		this.name=name;
		this.branches=new ArrayList<>();
	}
	
	public boolean addBranch(String name) {
		Branch newBranch=new Branch(name);
		Branch brance=findBranch(name);
		if(brance !=null) {
			branches.add(newBranch);
			System.out.println("Branch added successfully");
			return true;
		}
		return false;
	}
	
	public Branch findBranch(String branch) {
		ListIterator<Branch> iterator=branches.listIterator();
	    Branch branchu=null;
		while(iterator.hasNext()) {
			if(iterator.next().getName().equals(branch)) {
				 branchu=iterator.next();
				 System.out.println(branchu.getCustomers());
				 for(Customer x : branchu.getCustomers()) {
					 System.out.println(x.getName( ));
				 }
				 System.out.print("Branch found successfully");
			}
		}
		System.out.println(branches.size());
		return branchu;
		
	}
	
	public boolean addCustomer(String branch,String Customer,double amount) {
	if(!addBranch(branch)) {
		ListIterator<Branch> iterator=branches.listIterator();
		while(iterator.hasNext()) {
			if(iterator.next().getName().equals(branch)) {
				Branch branc=iterator.next();
				System.out.println("The Branch is obtained for customer");
				return branc.newCustomer(Customer, amount);
			}
		}
	}
		Branch branchu=new Branch(name);
		return branchu.addCustomerTransaction(Customer, amount);
	}
	
	public boolean addCustomerTransaction(String branch,String Customer,double amount) {
		    Branch branchu=findBranch(branch);
		    if(branchu != null) {
			return branchu.addCustomerTransaction(Customer, amount);
		}
		    return addCustomer(branch,Customer,amount);
	}
	public ArrayList<Customer> listCustomers(String branch,boolean printtransactions){
		Branch branchu=findBranch(branch);
		if(branchu != null) {
			return branchu.getCustomers();
		}
		 return new ArrayList<>();   
	}
	
	}

	

