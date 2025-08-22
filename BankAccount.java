package corejavaday_Two;

public class BankAccount {
    private long accountnumber;
    private int balance;
    private String customername;
    private String email;
    private long phone;

    public long getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(long accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int deposit(int amount) {
        balance += amount;
        return balance;
    }

    public int withdraw(int amount2) {
        balance -= amount2;
        if (balance <= 0) {
            balance=0;
        }
        return balance;
    }
}