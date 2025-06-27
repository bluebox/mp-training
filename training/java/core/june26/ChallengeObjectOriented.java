package com.tulasidhar.june26;

public class ChallengeObjectOriented {
	public static void main(String[] args) {
		BankAccount myAccount = new BankAccount(123,0,630,"Dasu","tulasidhar@gmail.com");
		
		myAccount.deposite(1000);
		myAccount.withdraw(2000);
		myAccount.withdraw(1000);
		myAccount.withdraw(-100);
	}
}
