package day5;

class Bank {
    private String name;

    public Bank(String name) {
        this.name = name;
    }

    class Account {
        private String accountHolder;
        private double balance;

        public Account(String accountHolder, double balance) {
            this.accountHolder = accountHolder;
            this.balance = balance;
        }

        public void showDetails() {
            System.out.println("Bank: " + name);
            System.out.println("Account Holder: " + accountHolder);
            System.out.println("Balance: $" + balance);
        }
    }

    public Account createAccount(String holder, double balance) {
        return new Account(holder, balance);
    }
}

