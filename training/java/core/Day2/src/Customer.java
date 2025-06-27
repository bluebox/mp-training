public class Customer {
    private String name;
    private int creditLimit;
    private String emailAdd;

    public String getName() {
        return name;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public Customer(String name,int creditLimit, String emailAdd){
        this.name=name;
        this.creditLimit=creditLimit;
        this.emailAdd=emailAdd;
    }
    public Customer(){
        this("babu",1000,"babu@123.com");
    }

    public Customer(String name,String emailAdd){
        this(name,0,emailAdd);
    }
}
