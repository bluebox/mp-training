package June26;

public class Bank {
	private int accountNumber;
	private int accountBalance;
	private String name;
	private String email;
	private String phoneNumber;
	
	public Bank(int accountNumber, int accountBalance, String name, String email, String phoneNumber) {
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void deposit (int amount) {
		this.accountBalance += amount;
	}
	
	public void withdraw (int amount) {
		if(this.accountBalance - amount >= 0)
			this.accountBalance -= amount;
		else System.out.println("Doesn't have sufficient amount.");
	}

	@Override
	public String toString() {
		return "Bank [accountNumber=" + accountNumber + ", accountBalance=" + accountBalance + ", name=" + name
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
