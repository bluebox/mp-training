package day2;

public class Account_Main {

    public static void main(String[] args) {

        Account newAccount = new Account();

        newAccount.setNumber("12345");
        newAccount.setBalance(1000.00);
        newAccount.setCustomerName("Kaushik");
        newAccount.setCustomerEmail("kaushik@gmail.com");
        newAccount.setCustomerPhone("123456789");
        
        newAccount.withdrawFunds(100.0);
        newAccount.depositFunds(250);
        newAccount.withdrawFunds(50);

        newAccount.withdrawFunds(200);

        newAccount.depositFunds(100);
        newAccount.withdrawFunds(45.55);
        newAccount.withdrawFunds(54.46);

        newAccount.withdrawFunds(54.45);
    }
}