package corejava.june26_Classes;

public class BankAccount {
	private String accountNumber;
	private double accountBalance;
	private String customerName;
	private String email;
	private String phoneNumber;
	public BankAccount(String accountNumber, double accountBalance, String customerName, String email, String phoneNumber) {
		this.accountNumber=accountNumber;
		this.accountBalance=accountBalance;
		this.customerName=customerName;
		this.email=email;
		this.phoneNumber=phoneNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
	
	public void deposite(double amount) {
		this.accountBalance+=amount;
	}
	public void withdrawl(double amount) {
		this.accountBalance-=amount;
		System.out.println("You have a withdrawal "+amount+" on Account Number: "+this.accountNumber+". Available balance is:"+this.accountBalance);
	}
	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", accountBalance=" + accountBalance + ", customerName="
				+ customerName + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
}
