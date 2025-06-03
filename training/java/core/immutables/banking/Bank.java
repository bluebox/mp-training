package banking;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bank {


    private final int routingNumber;
    private long lastTransactionId = 1;
    private final Map<String, BankCustomer> customers;

    public Bank(int routingNumber) {
        this.routingNumber = routingNumber;
        this.customers = new HashMap<>();
    }

    public BankCustomer getCustomer(String id) {
        return customers.get(id);
    }

    public void addCustomer(String name, double checkingInitialDeposit, double savingsInitialDeposit) {
        BankCustomer newCustomer = new BankCustomer(name, savingsInitialDeposit, checkingInitialDeposit);
        customers.put(newCustomer.getId(), newCustomer);
    }

    public boolean doTransaction(String id, AccountType type, BigDecimal amount) {
        BankCustomer customer = customers.get(id);

        if (customer != null) {
            BankAccount account = customer.getAccount(type);
            if (account != null) {
                if (Objects.requireNonNull(account.getBalance()).add(amount).doubleValue() < 0) {
                    System.out.println("\nInsufficient funds");
                } else {
                    account.commitTransaction(routingNumber, id, lastTransactionId++, amount.doubleValue());
                    return true;
                }
            }
        } else {
        	System.out.println("Invalid Customer Id");
        }

        return false;
    }
}