

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

 sealed abstract class BankAccount permits CheckingAccount, SavingsAccount, InvestmentAccount {
    private final String type;
    private final double balance;

    protected BankAccount(String type, double balance) {
        this.type = type;
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }
}

final class CheckingAccount extends BankAccount {
    public CheckingAccount(double balance) {
        super("Checking", balance);
    }
}

final class SavingsAccount extends BankAccount {
    public SavingsAccount(double balance) {
        super("Savings", balance);
    }
}

final class InvestmentAccount extends BankAccount {
    public InvestmentAccount(double balance) {
        super("Investment", balance);
    }
}

  class BankCustomer {
    private final String name;
    private final String id;
    private final List<BankAccount> accounts;

    public BankCustomer(String name, String id, List<BankAccount> accounts) {
        this.name = name;
        this.id = id;
        this.accounts = Collections.unmodifiableList(new ArrayList<>(accounts));
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }
}

 class SubBankCustomer extends BankCustomer {
    public SubBankCustomer(String name, String id, List<BankAccount> accounts) {
        super(name, id, accounts);
    }
}

public class ImmutableBankChallenge {
    public static void main(String[] args) {
        List<BankAccount> johnAccounts = new ArrayList<>();
        johnAccounts.add(new CheckingAccount(1500.0));
        johnAccounts.add(new SavingsAccount(5000.0));

        BankCustomer john = new BankCustomer("John Doe", "JD001", johnAccounts);
        System.out.println("Customer: " + john.getName());
        System.out.println("Accounts: " + john.getAccounts().size());

        List<BankAccount> janeAccounts = new ArrayList<>();
        janeAccounts.add(new SavingsAccount(2500.0));
        janeAccounts.add(new InvestmentAccount(10000.0));

        SubBankCustomer jane = new SubBankCustomer("Jane Smith", "JS002", janeAccounts);
        System.out.println("Customer: " + jane.getName());
        System.out.println("Accounts: " + jane.getAccounts().size());
    }
}