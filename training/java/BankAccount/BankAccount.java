package BankAccount;


public class BankAccount{
    private double balance;
    private String accountNumber;
    private String accountHolderName;
    private String email;
    private String phoneNumber;

    public void createAccount(double balance, String accountNumber, String accountHolderName, String email, String phoneNumber) {
        this.balance = balance;
        this.accountHolderName=accountHolderName;
        this.accountNumber=accountNumber;
        this.email=email;
        this.phoneNumber=phoneNumber;
        System.out.println("Account created Successfully!");
    }
    public void depositAmount(double amount){
         System.out.println("Balance before Deposit :" +balance);
        balance += amount;
        System.out.println("Balance after Deposit :" +balance);
        System.out.print("Thank You!");
    }
     public void withdrawAmount(double amount){
        if(balance < amount){
            System.out.println("Insufficient Funds");
            return;
        }
        System.out.println("Balance before Withdraw :" +balance);
        balance -= amount;
        System.out.println("Balance after Withdraw :" +balance);
        System.out.print("Thank You!");
    }
    
    public void setBalance(double balance){
        this.balance=balance;
    }
    public void setAccountNumber(String accountNumber){
        this.accountNumber= accountNumber;
    }
    public void setAccountHolderName(String accountHolderName){
        this.accountHolderName = accountHolderName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }    

    public double getBalance(){
        return balance;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public String getAccountHolderName(){
        return accountHolderName;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getDetails(){
        return "Account Number: "+this.accountNumber+"\n"+"Account Holder Name: "+this.accountHolderName+"\n";
    }
}
