class Encapsulation{
    public static void main(String[] args) {

        Person p = new Person();
      
        p.setName("surya");
        p.setBalance(10000);

        String AccountHolder = p.getName();
        double Balance = p.getBalance();

        System.out.println("Account holder is:"+AccountHolder);
        System.out.println("Balance is:"+Balance);

        p.withdrawAmount(10000.5);
    }
}