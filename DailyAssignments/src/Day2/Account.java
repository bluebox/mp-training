package Day2;

public class Account {

		private int accountNumber;
		private double balance;
		private String name;
		private String email;
		private String number;
		public Account(int accountNumber, double balance, String name, String email, String number) {
			super();
			this.accountNumber = accountNumber;
			this.balance = balance;
			this.name = name;
			this.email = email;
			this.number = number;
		}
		public int getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(int accountNumber) {
			this.accountNumber = accountNumber;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
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
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public void depositFunds(double amount) {
			if(amount>=500) {
			System.out.println("successfully deposited " +amount);
			}else {
				System.out.println("deposit min balance of 500");
			}
		}
		public void withdrawAmount(double amount) {
			if(amount>=500) {
			System.out.println("successfully withdrawed "+amount);
			
			}else {
				System.out.println("minimum withdraw amount is : 500");
			}
		}
	
		
}

