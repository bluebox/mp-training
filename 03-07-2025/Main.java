package com.prana;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount(BankAccount.AccountType.SAVINGS, 5000);
        BankAccount acc2 = new BankAccount(BankAccount.AccountType.CHECKING, 2000);
        BankAccount acc3 = new BankAccount(BankAccount.AccountType.SAVINGS, 9999);

        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(acc1);
        accounts.add(acc2);
        accounts.add(acc3);

        BankCustomer customer = new BankCustomer("Alice", "C1001", accounts);
        System.out.println(customer);
        accounts.add(new BankAccount(BankAccount.AccountType.FIXED, 10000));
        System.out.println("After modifying original list: " + customer);

        List<BankAccount> customerAccounts = customer.getAccounts();
        if (isModifiable(customerAccounts)) {
            customerAccounts.add(new BankAccount(BankAccount.AccountType.SAVINGS, 9999));
            System.out.println(" Customer's account list was modified (this should not happen!)");
        } else {
            System.out.println(" Customer's account list is immutable — cannot be modified.");
        }
    }

    private static boolean isModifiable(List<BankAccount> list) {
        try {
            list.add(null); 
            list.remove(null); 
            return true;
        } catch (UnsupportedOperationException e) {
            return false;
        }
    }
}
