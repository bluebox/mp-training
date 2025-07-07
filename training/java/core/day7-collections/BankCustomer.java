package day7;

import java.util.ArrayList;
import java.util.Arrays;

final public class BankCustomer {
	private static int lastCustomerId=101;
	private final int customerId;
	private final String name;
	private final ArrayList<BankAccount> accounts;

	public BankCustomer(String name, BankAccount... accounts) {
		this.customerId = lastCustomerId++;
		this.name = name;
		this.accounts = new ArrayList<>(Arrays.asList(accounts));
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public ArrayList<BankAccount> getAccounts() {
		return (ArrayList<BankAccount>) accounts.clone();
	}
	
	public void printDetails() {
		System.out.println(this);
		System.out.println("Accounts are:");
		accounts.forEach(System.out::println); 
	}

	@Override
	public String toString() {
		return "[customerId=" + customerId + ", name=" + name + "]";
	}

}
