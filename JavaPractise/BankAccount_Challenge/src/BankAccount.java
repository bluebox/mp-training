public class BankAccount {
	private long account_Number;
	private long account_Balance;
	private String customer_Name;
	private String email;
	private String phone_No;

	public BankAccount(long num, long bal, String name, String email, String phone_No) {
		account_Number = num;
		account_Balance = bal;
		customer_Name = name;
		this.email = email;
		this.phone_No = phone_No;
	}

	public void setAccountNumber(long num) {
		account_Number = num;
	}

	public void setAccountBalance(long num) {
		account_Balance = num;
	}

	public void setCustomerName(String name) {
		customer_Name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String num) {
		phone_No = num;
	}

	public long getAccountNumber() {
		return account_Number;
	}

	public long getAccountBalance() {
		return account_Balance;
	}

	public String getCustomerName() {
		return customer_Name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phone_No;
	}
	public void deposit(long amt) {
		account_Balance+=amt;
	}
	public void withdrawl(long amt) {
		if(account_Balance-amt<0) {
			System.out.println("Not sufficent funds");
		}
		else {
			account_Balance-=amt;
		}
	}
}

