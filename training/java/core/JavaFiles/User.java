
public class User {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BankACC acc=new BankACC(12345,"Karthik",100.00,"myemail@gmail.com","9347138148");
		
		System.out.println(acc);
		
		System.out.println("Account Balance : "+acc.getBalance());
		acc.deposit(200);
		acc.withdraw(1000);
		acc.withdraw(10.50);
		
	}

}
