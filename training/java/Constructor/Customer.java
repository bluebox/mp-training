package Constructor;

public class Customer {
    public String name;
    public double creditLimit;
    public String email;  
    public Customer(){
        this("Ram",100000.0d,"ram@gmail.com");
    }
    public Customer(String name, double creditLimit, String email) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.email = email;
    }
    public Customer(String name, String email){
        this(name,10000.0d,email);
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public double getCreditLimit(){
        return creditLimit;
    }
}
