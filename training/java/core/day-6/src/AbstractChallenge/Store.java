package AbstractChallenge;

import java.util.ArrayList;
import java.util.List;

public class Store {

	private List<ProductForSale> products;
	private List<orderItem> order;

	public Store() {
		this.products = new ArrayList<>();
		this.order = new ArrayList<>();
	}

	public void addProduct(ProductForSale product)
   {
	this.products.add(product);
   }

	public void displayProducts() {
		if (products.isEmpty()) {
			System.out.println("no products available");
			return;
		}
		for (int i = 0; i < products.size(); i++) {
			System.out.println("product ID: " + (i + 1));
			products.get(i).showDetails();
			System.out.println("----------------");
		}
	}

	public void addToOrder(int productId, int qty) {
		if (productId <= 0 || productId > products.size()) {
			System.out.println("invalid product Id");
			return;
		}
		ProductForSale selectedproduct = products.get(productId - 1);
		order.add(new orderItem(qty, selectedproduct));
		System.out.println("product added");
	}

	public void printOrder() {
		if (order.isEmpty()) {
			System.out.println("no items in the order");
			return;
		}
		double total = 0;
		for (orderItem item : order) {
			item.product().printPricedItem(item.qty());
			total += item.product().getSalesPrice(item.qty());
		}
		System.out.println("TOTAL : " + total);

	}
}
