
public class Credit {
    public static void main(String[] args) {
        Customer c1 = new Customer(); 
        System.out.println("Name: "+c1.getName() + "  $" + "CreditLimit: "+c1.getCreditLimit() + " Email: " + c1.getEmailAddress());

        Customer c2 = new Customer("Karthik", "Karthik@email.com");
        System.out.println("Name: "+ c2.getName() + "  $" + "CreditLimit: "+ c2.getCreditLimit() + " Email: " + c2.getEmailAddress());

        Customer c3 = new Customer("Karthikk", 50000.0, "Karthikk@email.com");
        System.out.println("Name: "+ c3.getName() + "  $" + "CreditLimit: "+ c3.getCreditLimit() + " Email: " + c3.getEmailAddress());
    }
}