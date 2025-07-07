package storeInventorySystem;

import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
        Store store = new Store();

        Product product1 = new Product("A001", "Laptop", "DEL", Category.ELECTRONICS);
        InventoryItem iventory1 = new InventoryItem(product1, 1000, 300, 100,150, 70000);
        store.addInventoryItem(iventory1);
        Cart cart = new Cart("C001",LocalDate.now(), CartType.PHYSICAL);
        cart.addItem(product1, 5);
        
        
        Product product2 = new Product("B001", "Cake", "Strabery", Category.FOOD);
        InventoryItem iventory2 = new InventoryItem(product2, 80, 20, 5, 10,20);
        store.addInventoryItem(iventory2);
        cart.addItem(product2, 3);
        
        
        store.manageStoreCarts(cart);

        System.out.println("Cart Sales Slip");
        cart.printSaleSlip();

        System.out.println("Check Out cart Details");
        store.checkOutCart(cart);

        System.out.println("Products Food Category");
        store.listProductsByCategory(Category.FOOD);
        
        System.out.println("Products Electronics Category");
        store.listProductsByCategory(Category.ELECTRONICS);
    }
}
