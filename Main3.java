public class Main3 {
    public static void main(String[] args) {
        Customer customer1 = new Customer(); 
        System.out.println(customer1.getName() + " | ₹" + customer1.getCreditLimit() + " | " + customer1.getEmailAddress());

        Customer customer2 = new Customer("Lakshmi", "lakshmi@email.com");
        System.out.println(customer2.getName() + " | ₹" + customer2.getCreditLimit() + " | " + customer2.getEmailAddress());

        Customer customer3 = new Customer("Ram", 75000.0, "ram@email.com");
        System.out.println(customer3.getName() + " | ₹" + customer3.getCreditLimit() + " | " + customer3.getEmailAddress());
    }
}
