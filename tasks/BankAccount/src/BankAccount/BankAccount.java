package BankAccount;

public class BankAccount {

    private final double balance;
    private final AccountType accountType;

    public enum AccountType {
        SAVINGS, CHECKING
    }

    public BankAccount(double balance, AccountType accountType) {
        this.balance = balance;
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}
