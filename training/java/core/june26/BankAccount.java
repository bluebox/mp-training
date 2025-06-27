package com.tulasidhar.june26;

//this is a helper class for a challenge
public class BankAccount {
	int accountNumber;
	private int accountBalance;
	int phoneNumber;

	String customerName;
	String email;
	public BankAccount(int accountNumber, int accountBalance, int phoneNumber, String customerName, String email) {
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.phoneNumber = phoneNumber;
		this.customerName = customerName;
		this.email = email;
	}
	
	public void withdraw(int withdrawAmount) {
		if(withdrawAmount > accountBalance) {
			System.out.println("Insufficient Balance");
		}
		else if(withdrawAmount < 0) {
			System.out.println("Cannot withdraw a negative amount");
		}
		else {
			accountBalance -= withdrawAmount;
			System.out.println("Transaction done , New balance:" + accountBalance);
		}
	}
	
	public void deposite(int depositAmount) {
		accountBalance += depositAmount;
		System.out.println("Deposit done , New Balance:"+accountBalance);
	}
	
}
