public class Bankaccount {
    private  int accountnumber;
    private  int accountbalance;
    private  String customername;
    private  String email;
    private  int phonenumber;

public int getAccountbalance() {
    return accountbalance;
}
public String getEmail() {
    return email;
}
public int getAccountnumber() {
    return accountnumber;
}
public String getCustomername() {
    return customername;
}
public int getPhonenumber() {
    return phonenumber;
}

  public void setAccountnumber(int accountnumber) {
      this.accountnumber = accountnumber;
  }
  public void setAccountbalance(int accountbalance) {
      this.accountbalance = accountbalance;
  }
  public void setCustomername(String customername) {
      this.customername = customername;
  }
  public void setEmail(String email) {
      this.email = email;
  }
  public void setPhonenumber(int phonenumber ) {
      this.phonenumber = phonenumber;
  }
  public void Depositmoney (int deposit){
    if(accountbalance<0){
        System.out.println("insufficent funds");

    }
   this.accountbalance += deposit;
    

  }
  public void withDrawMoney(int withdraw){
    if(accountbalance<0){
        System.out.println("insufficent funds");
    }
    this.accountbalance-= withdraw;
     

     
  }


}
