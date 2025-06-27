public class Account {
    private String accNumber;
    private int accBal;
    private String name;
    private String email;
    private String phone;


    public Account(String accNumber,int accBal, String name, String email,String phone){
        this.accNumber=accNumber;
        this.accBal=accBal;
        this.name=name;
        this.email=email;
        this.phone=phone;
    }
    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public int getAccBal() {
        return accBal;
    }

    public void setAccBal(int accBal) {
        this.accBal = accBal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void depositingFunds(int money){
        accBal+=money;
        System.out.println("new balance : "+accBal);
    }
    public void withdraw(int money){
        if(accBal-money<0){
            System.out.println("insufficient Balance");
        }
        else{
            System.out.println("money withdrew "+money+"balance : "+(accBal-money));
        }
    }
}
