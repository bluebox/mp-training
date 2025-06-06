package ImmutableClasses.copy2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BankCustomer {

	private final String name;
	private final int customerId;
	private final List<BankAccount> bankAccounts;
	public BankCustomer(String name, int customerId, List<BankAccount> bankAccounts) {
		this.name = name;
		this.customerId = customerId;
		this.bankAccounts = Collections.unmodifiableList(bankAccounts);
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return customerId;
	}

	public List<BankAccount> getAccounts() {
		return bankAccounts;

	}
	@Override
	public String toString() {
		return "Name: "+name+"\nCustomerId: "+customerId;
	}
}