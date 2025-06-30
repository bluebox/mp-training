
public class Main {

	public static void main(String[] args) {
		BankAccount b=new BankAccount(16517,5000,"Aakash","akashmadhunala@gmail.com","9640160484");
		System.out.println(b.getAccountNumber());
		b.deposit(500);
		System.out.println(b.getAccountBalance());
		b.withdrawl(2000);
		System.out.println(b.getAccountBalance());
		b.withdrawl(3600);
		System.out.println(b.getAccountBalance());
		b.withdrawl(3500);
		System.out.println(b.getAccountBalance());


	}

}
