package com.bank;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String arg[]) {
		BankConsumer b=new BankConsumer("Bhanu", "532235GD4T5FF4555", new ArrayList<>(List.of(new BankAccount("Savings", 20000),new BankAccount("Deposit", 10000))));
		System.out.println(b.toString());
		System.out.println("------------------------------------");
		b.addAccounts(new BankAccount("Checking", 30000));
		System.out.println(b.toString());
		BankConsumer b1 = new BankConsumer("Srinu", "53HYR5E34TG456", new ArrayList<>(List.of(new BankAccount("Savings", 20000))));
		b1=b;
		System.out.println(b1.toString());
	}
}
