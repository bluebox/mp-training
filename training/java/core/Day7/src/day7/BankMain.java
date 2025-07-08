package day7;

import java.util.ArrayList;
import java.util.List;

public class BankMain {
	 public static void main(String[] args) {
		List<BankAccount> accounts=new ArrayList<>();
		accounts.add(new BankAccount("SBI",BankAccount.type.SAVINGS,5000));
		accounts.add(new BankAccount("LIC",BankAccount.type.CURRENT,5500));
		accounts.add(new BankAccount("RBI",BankAccount.type.SAVINGS,3500));
		
		BankCustomer suri=new BankCustomer("Suri",accounts);
		System.out.println(suri);
		accounts.add(new BankAccount("PNB",BankAccount.type.CURRENT,1000));
		System.out.println(suri);
		
		
	}
}
