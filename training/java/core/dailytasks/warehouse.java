import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class warehouse {
    private List<order> shippingitems:
    public final static string[] PRODUCT_LIST  =
    {"Running shoes ", "boots", "sandles" ,"slippers","hip hops"};

    public warehouse() {
        this.shippingitems =new ArrayList<>();
    }
    }
public synchronized void receivedOrder(order item) {
    while(shippingitems.size() >20) {
        try {
            wait();
            catch(InterruptedException e) {
                throw new RuntimeErrorException(e)
            }
            }
            shippingitems.add(item);
            system.out.println("Incoming " + items);
            notifyAll();
        }
    }

    public synchronized Order fulfillOrder() {
        while (shippingitems.isEmpty()) {
            try {
                wait();
                Catch(InterruptedException e) {
                    throw new RuntimeErrorException(e);
                }
            }
            order Item  = shippingitems.remove(index:0);
            System.out.println("Fulfilled :" + item);
            notifyAll();
            return item;
         
        }

    }
