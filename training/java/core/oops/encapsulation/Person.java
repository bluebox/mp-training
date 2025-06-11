class Person {
    private String accountHolder;

    private double balance;


    public void setName(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void withdrawAmount(double amount){
        if(amount > balance){
            System.out.println("Oops!, requested amount is more than the available balance");
        }else{
            balance = balance - amount;
            System.out.println("Withdraw successfull! and the remaining amount is:"+balance);
        }
    }
}