import java.util.ArrayList;
import java.util.List;

public class Bank {
    public static void main(String[] args) {
        BankAccount ba=new BankAccount(Type.SAVINGS, 1000);
        BankAccount ba2=new BankAccount(Type.CHECKING, 2000);
        BankAccount ba3=new BankAccount(Type.CHECKING, 3000);
        BankCustomer bc1=new BankCustomer("rav", 12, ba2); 
        BankCustomer bc2=new BankCustomer("raju", 10, ba); 
        Banksubclass bsc=new Banksubclass("rakesh", 120, ba3);
        ba.setBalance(5000);
        System.out.println("tampered balance "+ba.getBalance());
        
        List<BankAccount> li=bc1.getAccounts();
        li.forEach(s->System.out.println(s));
    }
    
}
