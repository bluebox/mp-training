package day11.banking;

import java.util.ArrayList;

public class Bank {
private String name;
private ArrayList<Branch>branches;

Bank(String name){
	this.name=name;
	branches=new ArrayList<Branch>();
}
public boolean addBranch(String branch) {
	Branch bn=findBranch(branch);
	if(bn!=null)
		return false;
	branches.add(bn);
	return true;
}
public boolean addCustomer(String branch,String customer,double initialTransaction) {
	Branch bn=findBranch(branch);
	if(bn==null)
		return false;
	bn.newCustomer(customer, initialTransaction);
	return true;
}
public boolean addCustomerTransaction(String branch,String customer,double initialTransaction) {
	Branch bn=findBranch(branch);
	if(bn==null)
		return false;
	bn.addCustomerTransaction(customer,initialTransaction);
	return true;
}
public Branch findBranch(String branch) {
	int index=branches.indexOf(branch);
	if(index==-1)
		return null;
	return branches.get(index);
}
public boolean  listCustomers(String branch, boolean printTransactions) {
	if(!printTransactions)
		return false;
	
	int index=branches.indexOf(branch);
	if(index==-1)
		return false;
	
	Branch bd=branches.get(index);
	for(Customer c:bd.getCustomers())
		System.out.println(c.getName());
	return true;
	
	
}
}
