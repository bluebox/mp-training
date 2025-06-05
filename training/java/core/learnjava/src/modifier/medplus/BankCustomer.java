package modifier.medplus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankCustomer {
	private static int lastCustomerId = 10000000;
	private final String name;
	private final int customerId;
	
	private final List<BankAccount> accounts =new ArrayList<BankAccount>();
	
	public BankCustomer(String name,double savingBalance,double currentBalance) {
		this.name = name;
		customerId = lastCustomerId++;
		accounts.add(new BankAccount(BankAccount.type.SAVING,savingBalance));
		accounts.add(new BankAccount(BankAccount.type.CURRENT,currentBalance));
	}
	
	public String getName() {
		return name;
	}
	public List<BankAccount> getAccounts() {
		return new ArrayList<>(accounts);
	}
	public void addBankAccount(BankAccount bk) {
		accounts.add(bk);
	}

	public String toString() {
		String[] string = new String[accounts.size()];
		Arrays.setAll(string, i ->accounts.get(i).toString());
		return "Customer name : %s CustomerId : %015d%n\t%s%n".formatted(name,customerId,String.join("\n\t",string));
	}

}
