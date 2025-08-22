
public class MainCus1 {

    public static void main(String[] args) {
        Bank bank = new Bank("Trust Bank");

        bank.addCustomer("Lakshmi", 2000.0);
        bank.addTransaction("Lakshmi", -150.0);
        bank.addTransaction("Lakshmi", 500.0);

        bank.addCustomer("Pranamya", 1000.0);
        bank.addTransaction("Pranamya", -300.0);

        bank.printCustomerStatement("Lakshmi");
        bank.printCustomerStatement("Pranamya");
    }
}