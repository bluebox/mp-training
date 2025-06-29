package june26_Oops;

public class Account {
    // Fields (Attributes)
    private String accountNumber;
    private double balance;
    private String customerName;
    private String email;
    private String phoneNumber;

    // Constructor
    public Account(String accountNumber, double balance, String customerName, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Default Constructor
    public Account() {
    	this("0000", 0.0, "Default Name", "default@example.com", "0000000000");
        System.out.println("Empty constructor called.");
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Method to deposit funds
    public void depositFunds(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
        } else {
            balance += amount;
            System.out.println("Deposit of ₹" + amount + " successful. New balance is ₹" + balance);
        }
    }

    // Method to withdraw funds
    public void withdrawFunds(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (balance - amount < 0) {
            System.out.println("Insufficient balance. Withdrawal not processed.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal of ₹" + amount + " processed. Remaining balance is ₹" + balance);
        }
    }
}
