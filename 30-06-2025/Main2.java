public class Main2 {
    public static void main(String[] args) {
        Bank bank = new Bank("CodeTrust Bank");

        bank.addCustomer("Lakshmi", 2000.0);
        bank.addTransaction("Lakshmi", -150.0);
        bank.addTransaction("Lakshmi", 500.0);

        bank.addCustomer("Arjun", 1000.0);
        bank.addTransaction("Arjun", -300.0);

        bank.printCustomerStatement("Lakshmi");
        bank.printCustomerStatement("Arjun");
    }
}