package corejavaday_Two;

public class ClassChallenge {
        public static void main(String[] args) {
            BankAccount acc=new BankAccount();
            acc.setBalance(0);
            System.out.println(acc.deposit(500));
            System.out.println(acc.withdraw(600));
            System.out.println(acc.deposit(500));
            System.out.println(acc.withdraw(200));

        }

}
