package Jun5;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
		ArrayList<BankAccount> bank=new ArrayList<BankAccount>();
		bank.add(new BankAccount("business",999999.9));
		bank.add(new BankAccount("enterprise",9999.9));
		bank.add(new BankAccount("industry",9999999.9));

		BankCustomer customer1=new BankCustomer("raj",52522,bank);
		BankCustomer customer2=new BankCustomer("ravi",21212,bank);
		
	    ArrayList<BankAccount> list=new ArrayList<>();
	    for(var account:customer1.getAccounts()) {
	    	list.add(account);
	    }
	    System.out.println(bank.toString());
	    
	    System.out.println(customer1.toString());
	    System.out.println(customer2.toString());

	 
	}

}
