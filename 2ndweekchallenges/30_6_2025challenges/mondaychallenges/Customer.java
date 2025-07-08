import java.util.ArrayList;

public class Customer {
    
    private String name;
    private ArrayList<Double> transactions=new ArrayList<>();
    public Customer(String customername,double initransaction){
        this.name=customername;
        this.transactions.add(initransaction);
    }
    public String getName() {
        return this.name;
    }
    public ArrayList<Double> getTransactions() {
        return this.transactions;
    }
    public void addTransaction(double transaction){
        this.transactions.add(transaction);

    }
}
