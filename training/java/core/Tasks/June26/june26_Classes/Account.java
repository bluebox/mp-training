package corejava.june26_Classes;

public class Account {

	public static void main(String[] args) {
		BankAccount a=new BankAccount("1234456612",500.00,"Deepika","abc@gmail.com","1234567890");
		System.out.println(a.toString());
		a.deposite(1500);
		a.withdrawl(100);
		System.out.println(a.toString());
	}

}
