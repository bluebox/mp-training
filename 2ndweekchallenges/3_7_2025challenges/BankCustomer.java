import java.util.ArrayList;
import java.util.List;

public class BankCustomer {
    final private String customer_name;
    final private int customer_id;
    final private List<BankAccount> accounts=new ArrayList<>();
    public BankCustomer(String customer_name, int customer_id,BankAccount bankaccount) {
        this.customer_name = customer_name;
        this.customer_id = customer_id;
        this.accounts.add(new BankAccount(bankaccount.getType(), bankaccount.getBalance()));
    }
    public List<BankAccount> getAccounts() {
        List<BankAccount> li=new ArrayList<>();
        this.accounts.forEach(s->li.add(new BankAccount(s.getType(), s.getBalance())));
        return li;
    }
    @Override
    public String toString() {
        return "BankCustomer [customer_name=" + customer_name + ", customer_id=" + customer_id + ", accounts="
                + accounts + "]";
    }
    

    


}
