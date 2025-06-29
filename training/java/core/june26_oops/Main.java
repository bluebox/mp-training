package june26_Oops;

public class Main {
    public static void main(String[] args) {
        Account myAccount = new Account("123456789", 10000.0, "Greshma Ganta", "greshma@example.com", "9876543210");

        System.out.println("Initial balance: ₹" + myAccount.getBalance());

        myAccount.depositFunds(5000.0);

        myAccount.withdrawFunds(3000.0);


        myAccount.withdrawFunds(20000.0);

        
        myAccount.depositFunds(-500);

       
        myAccount.withdrawFunds(-100);
    }
}
