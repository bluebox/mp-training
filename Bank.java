import java.util.ArrayList;
public class Bank {
    private String name;
    private ArrayList<Customer> customers;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
        this.branches=new ArrayList<>();
    }

    
    public boolean addCustomer(String branchName,String customerName, double initialTransaction) {
        if (findCustomer(customerName) == null) {
            customers.add(new Customer(customerName, initialTransaction));
            return true;
        }
        return false;
    }
    
    public boolean addBranch(String branchName) {
        if (findBranch(branchName) == null) {
            branches.add(new Branch(branchName));
            return true;
        }
        return false;
    }

    public boolean addTransaction(String branchName,String customerName, double amount) {
        Customer existingCustomer = findCustomer(customerName);
        if (existingCustomer != null) {
            existingCustomer.addTransaction(amount);
            return true;
        }
        return false;
    }
    
    private Customer findCustomer(String customerName) {
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(customerName)) {
                return c;
            }
        }
        return null;
    }

    
    private Branch findBranch(String branchName) {
        for (Branch b : branches) {
            if (b.getName().equalsIgnoreCase(branchName)) {
                return b;
            }
        }
        return null;
    }

    public void listCustomers(String branchName,boolean printTrans) {
    	if(printTrans==true) {
        for (Customer c: customers) {
            System.out.println("Customer: " + c.getName());
            System.out.println("Transactions: ");
            for (Double d : c.getTransactions())
            { String res=d<0?"(debit)":"(credit)";
                System.out.println("  " + d+""+res);
            }
        }
    	}
    	else {
    		for (Customer c: customers) {
                System.out.println("Customer: " + c.getName());
    		}
    	}
    }
}
