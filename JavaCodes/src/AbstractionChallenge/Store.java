package AbstractionChallenge;

import java.util.ArrayList;

public class Store {

	public static void main(String[] args) {
		ArrayList<OrderItem> ans=new ArrayList<>();
		OrderItem a=new OrderItem(10,new ProductA("fast",20,"bring"));
		ans.add(a);
		ans.add(new OrderItem(20,new ProductB("fasdfs",30,"sdfjsdlfj")));
		for(OrderItem ele:ans) {
			ele.product.showDetaisl();
		}
	}

}
