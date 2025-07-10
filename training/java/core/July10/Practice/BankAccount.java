package July10.Practice;

public class BankAccount  {

	private String name;
	private double balance;

	public BankAccount(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public synchronized void deposit(int amount) {

		try {
			this.balance += amount;
			System.out.println("After " + amount + " is deposited successfully, Balance = " + balance);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void withdraw(int amount) {

		try {
			if (amount > balance)
				System.out.println("Doesn't have sufficient amount to withdraw");
			this.balance -= amount;
			System.out.println("After " + amount + " is writhdrawn successfully, Balance = " + balance);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
