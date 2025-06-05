package modifier.medplus;

public class Main {

	public static void main(String[] args) {
		
		BankAccount bk = new BankAccount(BankAccount.type.CURRENT,10000);
		BankCustomer bankcustomer = new BankCustomer("Saketh",10000,100000);
		System.out.println(bankcustomer);
		
		BankCustomer bankcustomer1 = new BankCustomer("Anil",10000,100000);
		System.out.println(bankcustomer1);
		
		bankcustomer1.getAccounts().clear();
		bankcustomer1.addBankAccount(bk);
		System.out.println(bankcustomer1);


	}

}
