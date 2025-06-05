package modifier.medplus;

public class BankAccount {
	public enum type {
		SAVING, CURRENT
	};

	private final type accountType;
	private final double balance;

	BankAccount(type accountType, double balance) {
		this.accountType = accountType;
		this.balance = balance;
	}

	public type getAccountType() {
		return accountType;
	}

	public double getBalance() {
		return balance;
	}
	
	public String toString() {
		return "%10s %.2f".formatted(accountType,balance);
	}

}
