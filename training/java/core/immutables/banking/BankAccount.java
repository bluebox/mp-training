package banking;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public final class BankAccount {
    private final AccountType accountType;
    private BigDecimal balance;
    private final Map<Long, Transaction> transactions = new LinkedHashMap<>();

    public BankAccount(AccountType accountType, double balance) {
        this.accountType = accountType;
        this.balance = BigDecimal.valueOf(balance);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance == null ? null : BigDecimal.valueOf(balance.doubleValue());
    }

    public Map<Long, String> getTransactions() {
        Map<Long, String> transactionsMap = new LinkedHashMap<>();
        for (var tx : transactions.entrySet()) {
            transactionsMap.put(tx.getKey(), tx.getValue().toString());
        }
        return transactionsMap;
    }

    void commitTransaction(int routingNumber, String customerId, long transactionId, double amount) {
        balance = balance.add(BigDecimal.valueOf(amount));
        transactions.put(transactionId, new Transaction(routingNumber,
                Integer.parseInt(customerId), transactionId, BigDecimal.valueOf(amount)));
    }

    @Override
    public String toString() {
        return " BankAccount { " +
                "accountType = " + accountType +
                ", balance = " + balance +
                '}';
    }
}