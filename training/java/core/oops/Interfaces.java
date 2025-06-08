package oops;
interface Bank{
	void loan();
	void account();
}
interface Atm extends Bank{
	void withdraw();
	void deposit();
}
abstract class Person implements Atm, Bank{
	public void withdraw() {
		System.out.println("Withdraw");
	}
	public void deposit() {
		System.out.println("Deposit");
	}
}
class User extends Person{
	public void loan() {
		System.out.println("Loan");
	}
	public void account() {
		System.out.println("Account");
	}
}
public class Interfaces {

	public static void main(String[] args) {
		User u = new User();
		u.loan();
		u.account();
		u.withdraw();
		u.deposit();
	}
}
