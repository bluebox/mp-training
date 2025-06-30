class BankAccount {
    private String accountNumber;
    private double accountBalance;
    private String customerName;
    private String email;
    private String phoneNumber;

    // Constructor
    public BankAccount(String accountNumber, double accountBalance, String customerName, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Default constructor
    public BankAccount() {
        this("000000", 0.0, "Default Name", "default@email.com", "0000000000");
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
        } else {
            accountBalance += amount;
            System.out.println("Deposited: " + amount + ", New Balance: " + accountBalance);
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (accountBalance - amount < 0) {
            System.out.println("Insufficient funds. Current balance: " + accountBalance);
        } else {
            accountBalance -= amount;
            System.out.println("Withdrew: " + amount + ", Remaining Balance: " + accountBalance);
        }
    }

    // Override toString() method to print object details
    @Override
    public String toString() {
        return "BankAccount {" +
                "\n  Account Number: " + accountNumber +
                "\n  Balance: " + accountBalance +
                "\n  Customer Name: " + customerName +
                "\n  Email: " + email +
                "\n  Phone: " + phoneNumber +
                "\n}";
    }
}

public class OOPChallenge {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("987654", 1200.50, "Alice Johnson", "alice@example.com", "9876543210");

        System.out.println(account); // This will use toString()

        account.deposit(250.00);
        account.withdraw(100.00);
        account.withdraw(2000.00); // Should show insufficient funds
    }
}

