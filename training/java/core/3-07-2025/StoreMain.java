

public class StoreMain {

	public static void main(String[] args) {
		Store store=new Store();
		Electronics laptop=new Electronics("refurbished",40000,"refursbished laptop",3);
		GroceryItem carrot=new GroceryItem("carrots",50,"fresh carrots",10);
		store.addItemToOrder(10, carrot);
		store.addItemToOrder(3, laptop);
		store.printOrders();
	}

}