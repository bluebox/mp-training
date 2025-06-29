package bankAccount;

public class BankAccount {
	private String accountnumber;
	private double accountBalance;
	private String customerName;
	private String email;
	private String phoneNumber;
	
	public BankAccount(String accountnumber, double accountBalance, String customerName, String email,
			String phoneNumber) {
		super();
		this.accountnumber = accountnumber;
		this.accountBalance = accountBalance;
		this.customerName = customerName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public String getAccountnumber() {
		return accountnumber;
	}
	
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	
	public void withdraw(double amount) {
		if(amount > accountBalance) {
			System.out.println("Insufficient Balance");
		}
		else if(amount < 0) {
			System.out.println("Withdrawal ammount cann't  nagative");
		}
		else {
			accountBalance -= amount;
			System.out.println("Withdrawal sucussful New Balance: "+accountBalance);
			
		}
	}
	
	public void deposite(int depositAmount) {
		if(depositAmount < 0) {
			System.out.print("Deposit ammount cann't nagative");
			return;
		}
		accountBalance += depositAmount;
		System.out.println("Deposit done sucessful New Balance: "+accountBalance);
	}
	
}
