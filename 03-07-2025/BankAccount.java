package com.prana;

public final class BankAccount {
    public enum AccountType { CHECKING, SAVINGS, FIXED }

    private final AccountType type;
    private final double balance;

    public BankAccount(AccountType type, double balance) {
        this.type = type;
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return type + " Account | Balance: $" + balance;
    }
}
