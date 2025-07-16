package corejava.july10_shoeWarehouse;

import java.util.*;

public class Warehouse {
    private List<Order> Shippingitems ;
    
    public final static String[] PRODUCT_LIST  =
    {"Running shoes ", "boots", "sandles" ,"slippers","hip hops"};

    public Warehouse() {
        this.Shippingitems =new ArrayList<>();
    }
    
    public synchronized void receivedOrder(Order Item) {
    	while(Shippingitems.size() > 20) {
    		try {
    			wait();
    		}
            catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Shippingitems.add(Item);
        System.out.println("Received  " + Item);
        notifyAll();
    }

    public synchronized void fulfillOrder() {
        while (Shippingitems.isEmpty()) {
            try {
                wait();
            }catch(InterruptedException e) {
                throw new RuntimeException(e);
             }
        }
        Order Item  = Shippingitems.remove(0);
        System.out.println("Fulfilled :" + Item);
        notifyAll(); 
    }
}