public class Bankaccount {
    private long accountnumber;
    private double balance;
    private String customername;
    private String email;
    private long phonenumber;

    public long getaccountnumber(){
        return accountnumber;

    }
    public double getbalance(){
        return balance;
    }
    public String getcustomername(){
        return customername;
    }
    public String getemail(){
        return email;
    }
    public long getphonenumber(){
        return phonenumber;
    }
public void setaccountnumber(long ac){
    accountnumber=ac;
        return ;

    }
    public void setbalance(double bal){
        balance=bal;
        return ;
    }
    public void setcustomername(String cn){
        customername=cn;
        return ;
    }
    public void setemail(String e){
        email=e;
        return ;
    }
    public void setphonenumber(long pn){
        phonenumber=pn;
        return ;
    }
    public void depositingfunds(long money){
        balance+=money;
        return;
    }
    public void withdrawing(long money){
        if(balance<money)return;
        balance-=money;
        return;
    }

}
