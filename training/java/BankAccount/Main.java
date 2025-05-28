package BankAccount;

public class Main {
    public static void main(String[] args) {
        BankAccount account1=new BankAccount();
        account1.createAccount(1000.00, "658745297434390", "John", "john@gmail.com", "9876543211");
        account1.getDetails();
        account1.depositAmount(500.00);
        account1.withdrawAmount(200.00);
        account1.getBalance();
    }
}
