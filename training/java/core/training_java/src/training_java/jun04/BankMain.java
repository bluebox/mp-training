package FinalandStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BankMain {

	public static void main(String[] args) {
		Bank bank=new Bank(2025,12l);
		bank.addCustomer("Mehra",65432.32,76543);
		BankCustomer customer=bank.getCustomer("1");
		bank.doTransaction("1",BankAccountType.SAVINGS,-982.21);
		BankAccount account=customer.getAccount(BankAccountType.SAVINGS);
		Map<Long,Transaction> t=account.getTransactions();
		t.forEach((k,v)->System.out.println(k+"-->"+v.toString()));
		
	}
	
}
