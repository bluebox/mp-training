package FinalandStreams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BankCustomer {

	private final String name;
	private final long custId;
	private final List<BankAccount> accounts;

	public BankCustomer(String name, long custId, List<BankAccount> accounts) {
		this.name = name;
		this.custId = custId;
		this.accounts = Collections.unmodifiableList(accounts);
	}

	public BankAccount getAccount(BankAccountType type) {
		for (var account : accounts) {
			if (account.getType().equals(String.valueOf(type))) {
				return account;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return String.valueOf(custId);
	}

	public List<BankAccount> getAccounts() {
		return new ArrayList<BankAccount>(accounts);

	}

	public String toString() {
		return name + " " + custId + " " + accounts.toString();
	}

}
