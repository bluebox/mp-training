

public class BankTest {
    public static void main(String[] args) {
        BANK account = new BANK();
        account.setAc_no("12345");
        account.setCustor_name("Alice");
        account.setEmail("alice@email.com");
        account.setPh_no("9876543210");
        account.setAcc_bal(1000);

        System.out.println("Initial balance: " + account.getAcc_bal());
        account.deposit(500);
        account.withdraw(200);
        account.withdraw(2000); // Should show insufficient funds
        System.out.println("Final balance: " + account.getAcc_bal());
    }
}