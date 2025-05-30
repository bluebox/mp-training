package BankingSystemChallenge;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("State Bank of India");

        bank.addBranch("Hitech City Hyderabad");

        bank.addCustomer("Hitech City Hyderabad", "Mukesh", 50.05);
        bank.addCustomer("Hitech City Hyderabad", "Rajesh", 175.34);
        bank.addCustomer("Hitech City Hyderabad", "Ramcharan", 220.12);

        bank.addCustomerTransaction("Hitech City Hyderabad", "Mukesh", 44.22);
        bank.addCustomerTransaction("Hitech City Hyderabad", "Rajesh", 12.44);
        bank.addCustomerTransaction("Hitech City Hyderabad", "Ramcharan", 1.65);

        bank.listCustomers("Hitech City Hyderabad", true);
        bank.listCustomers("Hitech City Hyderabad", false);
    }
}
