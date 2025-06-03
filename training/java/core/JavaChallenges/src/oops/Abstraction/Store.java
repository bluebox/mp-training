package oops.Abstraction;


import java.util.*;

public class Store {

	public static void main(String[] args) {
		ArrayList<OrderItem> ans=new ArrayList<>();
		OrderItem a=new OrderItem(10,new ProductA("a",1,"b"));
		ans.add(a);
		ans.add(new OrderItem(20,new ProductB("c",2,"d")));
		ans.add(new OrderItem(20,new ProductC("c",3,"d")));
		for(OrderItem ele:ans) {
			ele.product.showDetaisl();
		}
	}

}