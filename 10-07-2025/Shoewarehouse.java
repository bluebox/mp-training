package com.prana;

import java.util.ArrayList;
import java.util.List;

public class Shoewarehouse {
	
     private List<Order> shippingItems;
     final static String[] PRODUCT_LIST= {"Running Shoes", "Sandals", "Slippers", "Boots","High Tops"};

     
    public Shoewarehouse() {
    	this.shippingItems = new ArrayList<>();
    }
    
    
    public synchronized void receiveOrder(Order item) {
    	while(shippingItems.size() > 20) {
    		try {
    			wait();
    		}
    		catch(InterruptedException e) {
    			throw new RuntimeException(e);
    		}
    	}
    	shippingItems.add(item);
    	System.out.println("Incoming: "+ item);
    	notifyAll();
    }
    
    
    public synchronized Order fulfillOrder() {
    	while(shippingItems.isEmpty()) {
    		try {
    			wait();
    		}catch(InterruptedException e) {
    			throw new RuntimeException(e);
    		}
    	}
    	Order item = shippingItems.remove(0);
    	System.out.println("Fullfilled: "+ item);
    	notifyAll();
    	return item;
    	
    }
}
