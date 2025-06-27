
public class BankACC {

	private int AccNum;
	private String Name;
	private Double Balance;
	private String Email;
	private String Phno;
	
	
	
	public BankACC(int accNum, String name, Double balance, String email, String phno) {
		AccNum = accNum;
		Name = name;
		Balance = balance;
		Email = email;
		Phno = phno;
	}
	
	public int getAccNum() {
		return AccNum;
	}
	public void setAccNum(int accNum) {
		AccNum = accNum;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Double getBalance() {
		return Balance;
	}
	public void setBalance(Double balance) {
		Balance = balance;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhno() {
		return Phno;
	}
	public void setPhno(String phno) {
		Phno = phno;
	}
	
	@Override
	public String toString() {
		return "BankACC [AccNum=" + AccNum + ", Name=" + Name + ", Balance=" + Balance + ", Email=" + Email + ", Phno="
				+ Phno + "]";
	}
	
	public void deposit(double amt) {
		Balance+=amt;
		System.out.println("Transcation Sucessful Account Funds: "+Balance);
	}
	
	public void withdraw(double amt) {
		if(Balance-amt < 0) {
			System.out.println("Insufficient Funds");
		}else {
			Balance-=amt;
			System.out.println("Transcation sucessful Account Funds : "+ Balance);
		}
	}
	
	

}
