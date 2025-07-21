class Customer {
    // Fields
    private String name;
    private double creditLimit;
    private String emailAddress;

    // Constructor 1: All fields
    public Customer(String name, double creditLimit, String emailAddress) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.emailAddress = emailAddress;
    }

    // Constructor 2: No-args, using default values and calling Constructor 1
    public Customer() {
        this("Default Name", 500.0, "default@example.com");
    }

    // Constructor 3: Only name and email, calls Constructor 1
    public Customer(String name, String emailAddress) {
        this(name, 1000.0, emailAddress); // Assigning default credit limit
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}

public class ConstructorChallenge {
    public static void main(String[] args) {
        // Using constructor with all fields
        Customer customer1 = new Customer("Alice", 2000.0, "alice@example.com");
        System.out.println("Customer 1:");
        printCustomerInfo(customer1);

        // Using no-args constructor
        Customer customer2 = new Customer();
        System.out.println("\nCustomer 2:");
        printCustomerInfo(customer2);

        // Using constructor with name and email only
        Customer customer3 = new Customer("Bob", "bob@example.com");
        System.out.println("\nCustomer 3:");
        printCustomerInfo(customer3);
    }

    public static void printCustomerInfo(Customer customer) {
        System.out.println("Name: " + customer.getName());
        System.out.println("Credit Limit: " + customer.getCreditLimit());
        System.out.println("Email Address: " + customer.getEmailAddress());
    }
}

