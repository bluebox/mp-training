import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

enum AccountType { CHECKING, SAVINGS }

class Transaction {
    private final int routingNumber;
    private final String customerId;
    private final long transactionId;
    private final double amount;

    public Transaction(int routingNumber, String customerId, long transactionId, double amount) {
        this.routingNumber = routingNumber;
        this.customerId = customerId;
        this.transactionId = transactionId;
        this.amount = amount;
    }

    public int getRoutingNumber() { return routingNumber; }
    public String getCustomerId() { return customerId; }
    public long getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
}

class BankAccount {
    private final AccountType accountType;
    private double balance;
    private final Map<Long, Transaction> transactions = new HashMap<>();

    public BankAccount(AccountType accountType, double initialDeposit) {
        this.accountType = accountType;
        this.balance = initialDeposit;
    }

    public AccountType getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public Map<Long, Transaction> getTransactions() { return Collections.unmodifiableMap(transactions); }

    public boolean commitTransaction(int routingNumber, long transactionId, String customerId, double amount) {
        if (balance + amount < 0) return false;
        balance += amount;
        transactions.put(transactionId, new Transaction(routingNumber, customerId, transactionId, amount));
        return true;
    }
}

class BankCustomer {
    private final String name;
    private final String customerId;
    private final BankAccount checkingAccount;
    private final BankAccount savingsAccount;

    public BankCustomer(String name, String customerId, double checkingDeposit, double savingsDeposit) {
        this.name = name;
        this.customerId = customerId;
        this.checkingAccount = new BankAccount(AccountType.CHECKING, checkingDeposit);
        this.savingsAccount = new BankAccount(AccountType.SAVINGS, savingsDeposit);
    }

    public String getName() { return name; }
    public String getCustomerId() { return customerId; }
    public BankAccount getAccount(AccountType type) {
        return type == AccountType.CHECKING ? checkingAccount : savingsAccount;
    }
    public List<BankAccount> getAccounts() {
        return List.of(checkingAccount, savingsAccount);
    }
}

class Bank {
    private final int routingNumber;
    private final AtomicLong lastTransactionId = new AtomicLong(1);
    private final Map<String, BankCustomer> customers = new HashMap<>();

    public Bank(int routingNumber) {
        this.routingNumber = routingNumber;
    }

    public BankCustomer getCustomer(String id) {
        return customers.get(id);
    }

    public void addCustomer(String name, double checkingInitialDeposit, double savingsInitialDeposit) {
        String customerId = String.format("%015d", customers.size() + 1);
        customers.put(customerId, new BankCustomer(name, customerId, checkingInitialDeposit, savingsInitialDeposit));
    }

    public boolean doTransaction(String id, AccountType type, double amount) {
        BankCustomer customer = customers.get(id);
        if (customer == null) return false;
        long transactionId = lastTransactionId.getAndIncrement();
        return customer.getAccount(type)
            .commitTransaction(routingNumber, transactionId, id, amount);
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Bank bank = new Bank(123456789);
        bank.addCustomer("John Doe", 500.0, 1000.0);
        bank.addCustomer("Jane Smith", 1000.0, 2000.0);

        bank.doTransaction("000000000000001", AccountType.CHECKING, -200.0);
        bank.doTransaction("000000000000001", AccountType.SAVINGS, 500.0);
        bank.doTransaction("000000000000002", AccountType.CHECKING, -300.0);

        BankCustomer customer = bank.getCustomer("000000000000001");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Checking Balance: " + customer.getAccount(AccountType.CHECKING).getBalance());
        System.out.println("Savings Transactions: " + customer.getAccount(AccountType.SAVINGS).getTransactions().size());
    }
}