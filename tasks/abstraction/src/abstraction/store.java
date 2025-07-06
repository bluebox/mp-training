package abstraction;
import java.util.Scanner;
import java.util.ArrayList;
public class store {
    record orderitem(int qty, productforsale product){
        
    }
    public static void additem(ArrayList<orderitem> order, int index, int qty){
        order.add(new orderitem(qty,storeproducts.get(index)));
    }
	private static ArrayList<productforsale>  storeproducts=new ArrayList<productforsale>();
	public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        storeproducts.add(new artobject("0.oil paint",1350,"good one"));
        storeproducts.add(new artobject("1.powder",150,"good fragrance"));
        Listproducts();
        System.out.println("\n order1");
        var order1=new ArrayList<orderitem>();
        System.out.println("enter no items u want to buy:");
        int n=sc.nextInt();
        for(int i=0;i<n;i++){  
            System.out.println("enter item number:");
            int no=sc.nextInt();
            System.out.println("enter quantity:");
            int q=sc.nextInt();
        additem(order1,no,q);
        }
        printorder(order1);
		
	}
    public static void Listproducts(){
        for(var i:storeproducts){
            System.out.println("-".repeat(30));
            i.showdetails();
        }
    }

    public static void printorder(ArrayList<orderitem> order) {
        double saletotal = 0;
        for (orderitem item : order) {
            System.out.println("Product: " + item.product().getClass().getSimpleName() +
                               ", Qty: " + item.qty() +
                               ", Name: " + item.product().getType());
                               saletotal += item.product().getprice(item.qty());
            
        }
        System.out.println("total sale is"+saletotal);
    }
}
