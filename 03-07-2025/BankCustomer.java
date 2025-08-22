package com.prana;

import java.util.Collections;
import java.util.List;

public class BankCustomer {
    private final String name;
    private final String id;
    private final List<BankAccount> accounts;

    public BankCustomer(String name, String id, List<BankAccount> accounts) {
        this.name = name;
        this.id = id;
        this.accounts = List.copyOf(accounts); 
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<BankAccount> getAccounts() {
        return Collections.unmodifiableList(accounts); 
    }

    @Override
    public String toString() {
        return "Customer: " + name + " | ID: " + id + " | Accounts: " + accounts;
    }
}
