package day12.constructorchallenge;

public class VIPCustomer {
    private String name;
    private double creditLimit;
    private String email;

    public VIPCustomer() {
        this("Unknown VIP User",50000,"default@email.com");
        System.out.println("Empty Constructor call");
    }

    public VIPCustomer(String name, double creditLimit) {
        this(name, creditLimit, "default@email.com");
        System.out.println("Constructor call with 2 params");
    }

    public VIPCustomer(String name, double creditLimit, String email) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.email = email;
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