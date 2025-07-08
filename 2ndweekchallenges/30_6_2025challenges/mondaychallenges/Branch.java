import java.util.ArrayList;

public class Branch {
    private String name;
    private ArrayList<Customer> customers=new ArrayList<>();
    public Branch(String branchname){
        this.name=branchname;

    }
    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }
    public String getName() {
        return this.name;
    } 
    public boolean newCustomer(String customername,double initransaction){
        if(findCustomer(customername)==null)return true;
        
        return false;
    }
    public boolean addCustomerTransaction(String customername,double transaction){
        Customer customer=findCustomer(customername);
        if(customer!=null){
            customer.addTransaction(transaction);
            return true;
        }
        return false;
    }
    private Customer findCustomer(String customername){
        for(Customer customer:this.customers){
            if(customer.getName().equals(customername)){
                return customer;
            }
        }

        return null;

    }
    
}
