package com.Day2_;

public class Account_Main {

    public static void main(String[] args) {

        Account VardhanAccount = new Account();

        VardhanAccount.setNumber("12345");
        VardhanAccount.setBalance(1000.00);
        VardhanAccount.setCustomerName("Vardhan");
        VardhanAccount.setCustomerEmail("vardhan@gmail.com");
        VardhanAccount.setCustomerPhone("123456789");
        
        VardhanAccount.withdrawFunds(100.0);
        VardhanAccount.depositFunds(250);
        VardhanAccount.withdrawFunds(50);

        VardhanAccount.withdrawFunds(200);

        VardhanAccount.depositFunds(100);
        VardhanAccount.withdrawFunds(45.55);
        VardhanAccount.withdrawFunds(54.46);

        VardhanAccount.withdrawFunds(54.45);
    }
}