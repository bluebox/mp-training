package ImmutableBank;
import java.util.List;
public class ImmutableBankMain {
	
	public static void main(String[] args) {
		BankAcoount a1=new BankAcoount(AccountType.CHECKINGS,1000.00);
		BankAcoount a2=new BankAcoount(AccountType.SAVINGS,2000.00);
		
		List<BankAcoount> accounts=List.of(a1,a2);
		
		
		BankCustomer customers=new BankCustomer(123,"kar", accounts);
		
		System.out.println(customers.getCustomerId());
		
		customers.getAccounts().add(new BankAcoount(AccountType.CHECKINGS,100.00));
		System.out.println(customers.getAccounts());
	}

}
