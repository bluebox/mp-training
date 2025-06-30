public class Customer {

    private String name;
    private double creditLimit;
    private String email;

    public Customer(String name, double creditLimit, String email) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.email = email;
    }


    public Customer() {
        this("Akash", 1000.0, "akashmadhunala@email.com");
    }

    public Customer(String name, String email) {
        this(name, 5000, email);
    }
    public String getName() {
        return name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public String getEmail() {
        return email;
    }
}