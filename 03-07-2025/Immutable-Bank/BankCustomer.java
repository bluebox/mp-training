package ImmutableBank;
import  java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public final class BankCustomer{
	private final int customerId;
	private final String name;
	private final List<BankAcoount> Accounts;
	
	public BankCustomer(int customerId,String name,List<BankAcoount> Accounts) {
		this.customerId=customerId;
		this.name=name;
		this.Accounts=new ArrayList<>(Accounts);
	}

	protected int getCustomerId() {
		return customerId;
	}

	protected String getName() {
		return name;
	}

	protected List<BankAcoount> getAccounts() {
		return Collections.unmodifiableList(Accounts);
	}
	
}
