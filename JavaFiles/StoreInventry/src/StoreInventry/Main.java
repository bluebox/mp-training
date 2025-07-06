package StoreInventry;

public class Main {

	public static void main(String[] args) {
		Store store = new Store();

        Product apple = new Product("A1", "Apple", "FreshFarm", "Fruits");
        Product milk = new Product("M1", "Milk", "DairyPure", "Dairy");

        InventoryItem appleItem = new InventoryItem(apple, 100, 0, 20, 10, 0.5);
        InventoryItem milkItem = new InventoryItem(milk, 50, 0, 10, 5, 1.2);

        store.addInventoryItem(appleItem);
        store.addInventoryItem(milkItem);

        Cart cart = new Cart("C1", "physical");
        cart.addItem(apple);
        cart.addItem(milk);
        store.addCart(cart);

        cart.printSalesSlip();

        store.listProductsByCategory("Fruits");
        store.listProductsByCategory("Fruit");

	}

}
