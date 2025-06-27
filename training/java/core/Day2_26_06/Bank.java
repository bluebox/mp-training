package Day2_26_06;

public class Bank {
	private int accNo;
	private double balance;
	private String name;
	private String Mobile;
	private String email;
	
	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
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

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void deposit(double amount) {
		this.balance+=amount;
	}
	public void withdraw(double amount) {
		if(this.balance-amount<0) {
			System.out.println("Cannot withdraw amount " + amount);
		}else {
			this.balance=this.balance-amount;
		}
	}

	
	public Bank(int accNo, double balance, String name, String mobile, String email) {
		super();
		this.accNo = accNo;
		this.balance = balance;
		this.name = name;
		Mobile = mobile;
		this.email = email;
	}


	public static void main(String[] args) {
		Bank cust1=new Bank(1, 500, "saketh", "74635421", "ahybcxws@kjbn.com");
		System.out.println(cust1.getBalance());
	}
	
}
