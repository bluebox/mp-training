package Day16_18_07;

public class BankAccount {
	private String name;
	private double balance;
	private boolean isZeroAccount;
	public BankAccount(String name, double balance,boolean isZeroAccount) {
		this.name = name;
		this.balance = balance;
		this.isZeroAccount=isZeroAccount;
	}
	public BankAccount(String name, double balance) {
		this.name = name;
		this.balance = balance;
		this.isZeroAccount=false;
	}
	
	public boolean isZeroAccount() {
		return isZeroAccount;
	}
	public void exceptionThrower() throws Exception{
		throw new Exception("Manullly throwing exception.......");
	}
	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}
	public double withdraw(double amount) {
		balance-=amount;
		return balance;
	}
	public double deposit(double amount) {
		balance+=amount;
		return balance;
	}
		
}
