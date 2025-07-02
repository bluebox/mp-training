package day5;

public class Bank_Main {
    public static void main(String[] args) {
        Bank sbi = new Bank("SBI");
        Bank.Account acc = sbi.createAccount("Kaushik", 5000);
        acc.showDetails();
    }
}
