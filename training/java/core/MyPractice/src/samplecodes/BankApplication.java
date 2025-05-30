package samplecodes;
import java.util.*;
public class BankApplication {
	 public static void main(String[] args) {
	        Bank bank = new Bank("MyBank");
	        bank.addBranch("Downtown");
	        bank.addBranch("Uptown");
	        bank.addCustomer("Downtown", "Alice", 1000.00);
	        bank.addCustomer("Downtown", "Bob", 500.00);
	        bank.addCustomer("Uptown", "Charlie", 750.00);
	        bank.addCustomerTransaction("Downtown", "Alice", 200.00);
	        bank.addCustomerTransaction("Downtown", "Alice", -150.00);
	        bank.addCustomerTransaction("Downtown", "Bob", 300.00);
	        bank.addCustomerTransaction("Uptown", "Charlie", 100.00);
	        System.out.println("Listing customers for Downtown branch (no transactions):");
	        bank.listCustomers("Downtown", false);
	        System.out.println("Listing customers for Downtown branch (with transactions):");
	        bank.listCustomers("Downtown", true);
	        System.out.println("Listing customers for Uptown branch (with transactions):");
	        bank.listCustomers("Uptown", true);
	        if (!bank.addBranch("Downtown")) {
	            System.out.println("Downtown branch already exists!");
	        }
	        if (!bank.addCustomer("Suburb", "David", 200.00)) {
	            System.out.println("\nSuburb branch does not exist!");
	        }
	 }
}

class Bank {
    
    private String name;
    private ArrayList<Branch> branches;
    
    public Bank(String name){
        this.name = name;
        this.branches = new ArrayList<Branch>();
    }
    
    public boolean addBranch(String branchName){
        if(findBranch(branchName) != null){
            return false;
        }
        this.branches.add(new Branch(branchName));
        return true;
    }
    
    public boolean addCustomer(String branchName, String customerName, double initialTrans){
        Branch branch = findBranch(branchName);
        if(branch != null){
           return branch.newCustomer(customerName,initialTrans);
            
        }
        return false;
    }
    public boolean addCustomerTransaction(String branchName, String customerName, double transaction){
        Branch branch = findBranch(branchName);
        if(branch != null){
           return branch.addCustomerTransaction(customerName,transaction);
        }
        return false;
    }
    
    private Branch findBranch(String branchName){
        for(int i=0;i<this.branches.size();i++){
            Branch branch = this.branches.get(i);
            if(branch.getName().equals(branchName)){
                return branch;
            }
        }
        return null;
    }
    
    public boolean listCustomers(String branchName, boolean printTransaction){
        Branch branch = findBranch(branchName);
        if(branch != null){
            System.out.println("Customer details for branch "+branch.getName());
            ArrayList<Customer> branchCustomers = branch.getCustomers();
            for(int i=0;i<branchCustomers.size();i++){
                Customer branchCustomer = branchCustomers.get(i);
                System.out.println("Customer: "+branchCustomer.getName()+"["+(i+1)+"]");
                
                if(printTransaction){
                    System.out.println("Transactions");
                    ArrayList<Double> transactions = branchCustomer.getTransactions();
                    for(int j=0;j<transactions.size();j++){
                        System.out.println("["+(j+1)+"] Amount "+ transactions.get(j));
                    }
                }
            }
            return true;
        }else{
            return false;
        }
    }
}
class Branch {
    
    private String name;
    private ArrayList<Customer> customers;
    
    public Branch(String branchName){
        this.name = branchName;
        this.customers = new ArrayList<Customer>();
    }
    
    public String getName(){
        return name;
    }
    
    public ArrayList<Customer> getCustomers(){
        return customers;
    } 
    
    public boolean newCustomer(String customerName,double transaction){
        Customer customer = findCustomer(customerName);
        if(customer == null){
            this.customers.add(new Customer(customerName,transaction));
            return true;
        }
        return false;
    }
    
    public boolean addCustomerTransaction(String customerName, double transaction){
        
        Customer customer = findCustomer(customerName);
        if(customer != null){
            customer.addTransaction(transaction);
                return true;
        }
        return false;
    }
    
    private Customer findCustomer(String customerName){
        for(int i=0;i<customers.size();i++){
            Customer customer = this.customers.get(i);
            if(customer.getName().equals(customerName)){
                return customer;
            }
        }
        return null;
    }
}
class Customer {
    private String name;
    private ArrayList<Double> transactions;
    
    public Customer(String name,double transaction){
        this.name = name;
        this.transactions = new ArrayList<Double>();
        addTransaction(transaction);
    }
    
    public String getName(){
        return name;
    }
    
    public ArrayList<Double> getTransactions(){
        return transactions;
    }
    
    public void addTransaction(double transaction){
        this.transactions.add(transaction);
    }
}
