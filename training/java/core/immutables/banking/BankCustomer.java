package banking;

import java.util.ArrayList;
import java.util.List;

public class BankCustomer {
    private static int LAST_ID = 10_000_000;

    private final int id;
    private final String name;
    private final List<BankAccount> accounts;

    BankCustomer(String name, double savings, double checking) {
        this.id = LAST_ID++;
        this.name = name;
        this.accounts = new ArrayList<>(List.of(
                new BankAccount(AccountType.SAVINGS, savings),
                new BankAccount(AccountType.CHECKING, checking)));
    }

    public String getId() {
        return "%015d".formatted(id);
    }

    public String getName() {
        return name;
    }

    public List<BankAccount> getAccounts() {
        return accounts == null ? null : List.copyOf(accounts);
    }

    public BankAccount getAccount(AccountType type) {
        for (BankAccount account : accounts) {
            if (account.getAccountType() == type) {
                return account;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "\nBankCustomer\n{ " +
                "\n\tid = " + id +
                ", \n\tname = '" + name + '\'' +
                ", \n\taccounts = " + accounts +
                "\n}";
    }
}