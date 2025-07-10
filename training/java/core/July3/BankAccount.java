package July3;

public class BankAccount {
	
	private AccountType type;
	private double initialAmount;
	
	public BankAccount(AccountType type, double initialAmount) {
		this.type = type;
		this.initialAmount = initialAmount;
	}

	public double getInitialAmount() {
		return initialAmount;
	}

	public AccountType getType() {
		return type;
	}
	
}
