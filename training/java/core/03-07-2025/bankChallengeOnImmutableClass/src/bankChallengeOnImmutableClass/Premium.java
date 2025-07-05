package bankChallengeOnImmutableClass;

import java.util.List;

public class Premium extends BankCustomer {
  private final double bonus;

public Premium(String customerName,String customerId,List<BankAccount>accounts, double bonus) {
	super(customerName,customerId,accounts);
	this.bonus = bonus;
}

public double getBonus() {
	return bonus;
}

public String toString() {
    return "PremiumBankCustomer{" +
           "customerName='" +getCustomerName()+ '\'' +
           ", customerId='" +getCustomerId()+ '\'' +
           ", accounts=" +getAccounts()+
           ", bonusPoints=" + bonus +
           '}';
}

  
}
