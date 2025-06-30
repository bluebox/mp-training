package com.tulasidhar.june30.AutoBoxingChallenge;

import java.util.ArrayList;

public class AutoBoxingChallenge {
	public static void main(String[] args) {
		Bank bank  = new Bank("BankOfBaroda");
		Customer dasu = new Customer("dasu");
		
		bank.addCustomer(dasu);
		bank.addTransaction(dasu, 100);
		bank.addTransaction(dasu, 20);
		bank.addTransaction(dasu, -10);
		
		bank.printStatement(dasu);
	}
	
	
}

class Customer {
	String name;
	ArrayList<Double> transactions;
	
	public Customer(String name) {
		transactions = new ArrayList<Double>();
		this.name = name;
	}
	
}


class Bank{
	String name;
	ArrayList<Customer> customers;
	
	public Bank(String name) {
		this.name = name;
		customers = new ArrayList<Customer>();
	}
	
	public void addCustomer(Customer customer) {
		if(customers.contains(customer)) {
			System.out.println("Customer already exists");
			return;
		}
		customers.add(customer);
	}
	
	public void addTransaction(Customer customer,double transaction) {
		if(customers.contains(customer)) {
			customer.transactions.add(transaction);
		}
		else {
			System.out.println("Customer doesn't exist");
		}
	}
	
	public void printStatement(Customer customer) {
		System.out.println("Customer Name:" + customer.name);
		for(double transaction : customer.transactions) {
			System.out.println(transaction);
		}
	}
	
	
	
}