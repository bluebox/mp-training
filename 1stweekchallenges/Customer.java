public class Customer {
    private String name;
    private long credit_limit;
    private String email_address;
    public long getCredit_limit() {
        return credit_limit;
    }
    public String getEmail_address() {
        return email_address;
    }
    public String getName() {
        return name;
    }
    public Customer(String name,String email_address,long credit_limit){
        this.email_address=email_address;
        this.name=name;
        this.credit_limit=credit_limit;

    }
    public Customer(){
        this("", "", 0);
    }
    public Customer(String name,String email_address){
        this(name, email_address, 0);
    }

}
