public class Main {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("12345", 1000.0, "Lakshmi Pranamya", "lakshmi@email.com", "9876543210");

        System.out.println("Initial balance: ₹" + myAccount.getBalance());

        myAccount.deposit(500.0);         
        myAccount.withdraw(200.0);        
        myAccount.withdraw(1500.0);       
        myAccount.deposit(-100);          
        myAccount.withdraw(-50);          

        System.out.println("Final balance: ₹" + myAccount.getBalance());
    }
}
