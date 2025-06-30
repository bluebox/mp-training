package day4;

import java.util.ArrayList;

public class AutoBoxingChallenge {
	public static void main(String[] args) {
		Bank b1=new Bank("SBI");
		Customer c1=new Customer("Babu");
		b1.addCustomer(c1);
		b1.addTransaction(c1, 1200);
		b1.addTransaction(c1, -500);
		b1.addTransaction(c1, 300);
		b1.printStatement(c1);
		
	}
}
 
class Customer{
	private String name;
	ArrayList<Double> transactions;
	
	public Customer(String name) {
		this.name=name;
		transactions=new ArrayList<Double>();
	}
	
	public String getName() {
		return name;
	}
}
class Bank{
	private String name;
	ArrayList<Customer> customers;
	
	public Bank(String name) {
		this.name=name;
		customers=new ArrayList<Customer>();
	}
	
	public void addCustomer(Customer customer) {
		if(!customers.contains(customer)) {
			customers.add(customer);
		}
		else {
			System.out.println("User alerady Exists");
		}
	}
	
	public void addTransaction(Customer customer,double Transaction) {
		if(customers.contains(customer)) {
			customer.transactions.add(Transaction);
			return;
		}
		else {
			System.out.println("User doesn't exist ");
		}
	}
	
	public void printStatement(Customer customer) {
		if(customers.contains(customer)) {
			System.out.println("Name : "+customer.getName()+" \nuser of Bank : "+name);
			for(double transaction : customer.transactions) {
				System.out.println(transaction<0?"Debited "+transaction:"Credited "+transaction);
			}
		}
	}
}