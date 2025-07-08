package day_2_7_25.bank;
import java.util.*;

import day_2_7_25.bank.BankAccount.AccountType;


public class Bankcustomer {
   private final String name;
   private final int cust_id;
   final List<BankAccount> list;
   
   {
	   list=new LinkedList<>();
   }
   public Bankcustomer(String name,int cust_id,List<BankAccount> accounts) {
	   this.name=name;
	   this.cust_id=cust_id;
       createAccounts();
   }
   
   private void createAccounts() {
	   EnumSet.allOf(AccountType.class).forEach(s->{
		   list.add(new BankAccount(500,s));
	   });
   }
   
   
   public List<BankAccount> getAccounts(){
	   return list;
   }
   
   
   
   
   
}
