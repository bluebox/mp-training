import java.util.*;
public class Store {
    static ArrayList<ProductForSale> products=new ArrayList<>();
    
    public record OrderItem(int qty,ProductForSale product) {

    }
    
    public static void addItemToOrder(ArrayList<OrderItem> order,ProductForSale product,int qty){
       order.add(new OrderItem(qty, product));
        


    }
    public static ArrayList printOrder(ArrayList<OrderItem> order){
        return order;
        
        

    }
    public static void main(String[] args) {
        
        ArrayList<OrderItem>order=new ArrayList<>();
        Pen p=new Pen("stationary",5.0,"to write");
        Scale s=new Scale("stationary",10.0,"to draw lines");
        Salt st=new Salt("grocery",20.0,"adds taste to food");
        products.add(p);
        products.add(s);
        products.add(st);
        int i=0;
        for(ProductForSale pfs:products){
            
            addItemToOrder(order,pfs,i);
            i++;
        }

        

        
        
    }

}
