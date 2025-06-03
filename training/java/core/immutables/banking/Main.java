package banking;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(321231235);
        bank.addCustomer("Ravi", 5000.0, 10000.00);
        BankCustomer customer = bank.getCustomer("000000010000000");
        System.out.println(customer);

        if (bank.doTransaction(customer.getId(), AccountType.CHECKING, new BigDecimal(35))) {
            System.out.println(customer);
        }

        if (bank.doTransaction(customer.getId(), AccountType.CHECKING, BigDecimal.valueOf(-535))) {
            System.out.println(customer);
        }

        if (bank.doTransaction(customer.getId(), AccountType.CHECKING, BigDecimal.valueOf(-535.01))) {
            System.out.println(customer);
        }

        BankAccount checking = customer.getAccount(AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((k, v) -> System.out.println("\n"+k + ": " + v));
    }
}