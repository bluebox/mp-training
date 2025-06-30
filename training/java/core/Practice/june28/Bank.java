package Practice.june28;

public class Bank {
	private final double balance;
	private String accountName;
	public Bank(double balance,String accountName) {
		this.setAccountName(accountName);
		this.balance=balance;
	}
	public double getBalance() {
		return balance;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
