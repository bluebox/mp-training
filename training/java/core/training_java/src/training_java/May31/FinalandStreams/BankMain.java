package FinalandStreams;

import java.util.ArrayList;
import java.util.List;

public class BankMain {

	public static void main(String[] args) {
		List<BankAccount> bac1=new ArrayList<BankAccount>();
		bac1.add(new BankAccount("savings",65432.32));
		bac1.add(new BankAccount("checkings",762712.34));
		BankCustomer b1=new BankCustomer("Mehra",23456L,bac1);
		BankCustomer b2=new BankCustomer("Raghav",23236L,bac1);
	    List<BankAccount> arr=new ArrayList<>();
	    for(var account:b1.getAccounts()) {
	    	arr.add(account);
	    }
	    arr.set(0,new BankAccount("checkings",1234));
//	    arr.add(new BankAccount("Savings",34678));
	    System.out.println(b1.toString()+"\n"+arr.toString());
	 
	}
	
}
