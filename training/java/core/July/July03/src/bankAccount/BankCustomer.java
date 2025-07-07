package bankAccount;

import java.util.ArrayList;
import java.util.List;

public class BankCustomer {
	
	final private String customerName;
	final private int customerId;
	final private List<BankAccount> accounts;
	
	public BankCustomer(String customerName, int customerId, BankAccount account) {
		
		this.customerName = customerName;
		this.customerId = customerId;
		this.accounts = new ArrayList<>();
		this.accounts.add(account);
		
	}

	public String getCustomerName() {
		return customerName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public List<BankAccount> getAccounts() {
		return  new ArrayList<>(accounts);
	}
	
	public void addAccount(BankAccount account) {
		this.accounts.add(account);
	}

	@Override
	public String toString() {
		return "BankCustomer [customerName=" + customerName + ", customerId=" + customerId + ", accounts=" + accounts
				+ "]";
	}
	
}
