package com.prana;

public class MainStore {
    public static void main(String[] args) {
        Store store = new Store();

        Product milk = new Product("101", "Milk", "DairyFresh", "Dairy");
        Product apple = new Product("102", "Apple", "NatureFarm", "Fruits");

        store.addProductToInventory(new InventoryItem(milk, 30, 5, 20, 45.0));
        store.addProductToInventory(new InventoryItem(apple, 50, 10, 30, 10.0));

        Cart cart1 = new Cart(1, Cart.Type.PHYSICAL);
        cart1.addItem(milk, 2);
        cart1.addItem(apple, 4);

        store.addCart(cart1);

        store.listProductsByCategory();
        store.checkoutCart(cart1);

        store.abandonOldCarts();
    }
}
