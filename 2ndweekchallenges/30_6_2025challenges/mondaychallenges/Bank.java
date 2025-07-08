import java.util.*;
public class Bank {
    
    private String name;
    private ArrayList<Branch>  branches=new ArrayList<>();
    public Bank(String name){
        this.name=name;

    }
    public boolean addBranch(String branchname){
        if(findBranch(branchname)!=null){
            System.out.println("already exists");
            return false;
        }
        Branch branch=new Branch(branchname);
        branches.add(branch);

        return true;
    }
    public boolean addCustomer(String branchname,String customer,double initransaction){
         Branch branch=findBranch(branchname);
        if(branch.newCustomer(customer, initransaction)){
            ArrayList<Customer> customers=branch.getCustomers();
            Customer cust=new Customer(customer, initransaction);
            customers.add(cust);
            return true;
        }
        return false;
    }
    public boolean addCustomerTransaction(String branchname,String customer,double transaction){
        Branch branch=findBranch(branchname);
        if(branch.addCustomerTransaction(customer, transaction)){
            return true;
        }
        return false;

    }
    private Branch findBranch(String branchname){
        for(Branch branch:this.branches){
            if(branch.getName().equals(branchname)){

                return branch;
            }
            
        }
        return null;

    }
    public boolean listCustomers(String branchname,boolean printtransactions){
        Branch branch=findBranch(branchname);
        if(branch!=null){
            ArrayList<Customer>customers=branch.getCustomers();
            int i=0;
            for(Customer customer:customers ){
                System.out.println("["+i+"]"+customer.getName());
                if(printtransactions){
                    ArrayList<Double>transcations=customer.getTransactions();
                    int j=0;
                    for(Double transcation:transcations){
                        System.out.println("["+j+"]"+"Amount"+transcation);
                        j++;
                    }
                }
                i++;
            }
            return true;
        }
        return false;
        

    } 
    public String getName() {
        return name;
    }
    public static void main(String[] args) {
        Bank bank=new Bank("National Aus bank");
        System.out.println("bankname"+bank.getName());
        boolean c=bank.addBranch("adelaide");
        System.out.println(c);
        boolean k=bank.addCustomer("adelaide", "Tim", 50.05);
        System.out.println(k);
                bank.addCustomer("adelaide", "Mike", 1750.05);
                        bank.addCustomer("adelaide", "Percy", 220.05);
        bank.addCustomerTransaction("adelaide","Tim",44.22);
                boolean b=bank.addCustomerTransaction("adelaide","Tim",12.22);
        System.out.println(b);
        System.out.println(bank.addCustomerTransaction("adelaide","Mike",1.65));
        bank.listCustomers("adelaide", true);




    }


}
