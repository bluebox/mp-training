package bankChallengeOnImmutableClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

 class BankCustomer {
 private final String customerName;
 private final String customerId;
 private final List<BankAccount>accounts;
public BankCustomer(String customerName, String customerId, List<BankAccount> accounts) {
	super();
	this.customerName = customerName;
	this.customerId = customerId;
	this.accounts = Collections.unmodifiableList(new ArrayList<>(accounts));
}
public String getCustomerName() {
	return customerName;
}
public String getCustomerId() {
	return customerId;
}
public List<BankAccount> getAccounts() {
	return accounts;
}
public String toString() {
    return "BankCustomer{" +
           "customerName='" + customerName + '\'' +
           ", customerId='" + customerId + '\'' +
           ", accounts=" + accounts +
           '}';
}
 
}
