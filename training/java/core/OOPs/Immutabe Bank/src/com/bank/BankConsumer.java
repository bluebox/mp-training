package com.bank;

import java.util.List;

public final class BankConsumer{
	private final String name;
	private final String customerId;
	private final List<BankAccount> accounts;
	public BankConsumer(String name,String customerId,List<BankAccount> accounts) {
		this.name=name;
		this.customerId=customerId;
		this.accounts=accounts;
	}
	public void addAccounts(BankAccount b) {
		this.accounts.add(b);
	}
	public String toString() {
		System.out.println("Customer name : "+name);
		System.out.println("Customer ID : "+customerId);
		System.out.println("List of Bank accounts are :");
		for(BankAccount i:accounts) {
			System.out.println("Account type : "+i.accountType);
			System.out.println("Balance : "+i.balance);
			System.out.println("------------------------------------");
		}
		return "Customer name : "+name+"\nCustomer ID : "+customerId+"\nList of Bank accounts are :"+accounts;
	}
	public String getName() {
		return name;
	}
	public String getCustomerId() {
		return customerId;
	}
}
