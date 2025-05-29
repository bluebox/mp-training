package ArrayListChallenge;

import java.util.ArrayList;

class Bank{
	private String name;
	private ArrayList<Branch>branches;
	public Bank(String name) {
		this.name=name;
		branches=new ArrayList<>();
	}
	public boolean addBranch(String name) {
		for(Branch ele:branches) {
			if(ele.getName()==name)return false;
		}
		branches.add(new Branch(name));
		return true;
	}
	public boolean addCustomer(String branchName,String name,double amount) {
		for(Branch ele:branches) {
			if(ele.getName()==branchName) {
				return ele.newCustomer(name, amount);
			}
		}
		return false;
	}
	public boolean addCustomerTransaction(String branchName,String name,double amount) {
		for(Branch ele:branches) {
			if(ele.getName()==branchName) {
				return ele.addCustomerTransaction(name, amount);
			}
		}
		return false;
	}
	public Branch findBranch(String name) {
		for(Branch ele:branches) {
			if(ele.getName()==name)return ele;
		}
		return null;
	}
	public boolean listCustomers(String name,boolean temp) {
		boolean val=false;
		Branch a=null;
		for(Branch ele:branches) {
			if(ele.getName()==name) {
				a=ele;
				val=true;
				break;
			}
		}
		if(!val)return false;
		if(temp) {
			ArrayList<Customer>ans=a.getCustomers();
			for(Customer ele:ans) {
				System.out.println(ele.getName());
				ArrayList<Double>trans=ele.getTransactions();
				for(Double i:trans) {
					System.out.print(i+" ");
				}
				System.out.println();
				System.out.println("total amount="+ele.getTotalAmount());
			}
		}
		return true;
	}
}
class Branch{
	private String name;
	private ArrayList<Customer>customers;
	public Branch(String name) {
		this.name=name;
		customers=new ArrayList<>();
	}
	public String getName() {
		return this.name;
	}
	public ArrayList<Customer>getCustomers(){
		return this.customers;
	}
	public boolean newCustomer(String name,double amount) {
		for(Customer ele:customers) {
			if(ele.getName()==name)return false;
		}
		customers.add(new Customer(name,amount));
		return true;
	}
	public boolean addCustomerTransaction(String name,double amount) {
		for(Customer ele:customers) {
			if(ele.getName()==name) {
				ele.addTransaction(amount);
				return true;
			}
		}
		return false;
	}
	public Customer findCustomer(String name) {
		for(Customer ele:customers) {
			if(ele.getName()==name)return ele;
		}
		return null;
	}
}
class Customer{
	private String name;
	private ArrayList<Double>transaction=new ArrayList<>();
	public Customer(String name,double initial) {
		this.name=name;
		transaction.add(initial);
	}
	public String getName() {
		return this.name;
	}
	public ArrayList<Double> getTransactions(){
		return transaction;
	}
	public void addTransaction(double val) {
		transaction.add(val);
	}
	public double getTotalAmount() {
		double sum=0;
		for(Double ele:transaction)sum+=ele;
		return sum;
	}
}
public class BankingSystemChallenge {

	public static void main(String[] args) {
		Bank bank=new Bank("sbi");
		bank.addBranch("marikal");
		bank.addCustomer("marikal", "anand", 1000);
		bank.addCustomer("marikal", "abhi", 2000);
		bank.addCustomerTransaction("marikal", "anand", 10000);
		bank.addCustomerTransaction("marikal", "abhi", 1000);
		bank.listCustomers("marikal",true);
		bank.addBranch("basar");
		bank.addCustomer("basar", "sai", 10000);
		System.out.println(bank.addCustomerTransaction("basar", "sai",10000));
		bank.listCustomers("basar", true);
	}

}
