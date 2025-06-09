package StoreInventorySystem_CollectionsFrameworkChallenge_In_Progress;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();

        Product milkPacket = new Product("SKU001", "Milk", "Heritage", Category.DAIRY);
        Product wallPaint = new Product("SKU002", "Paint", "Asian Paints", Category.PAINT);

        InventoryItem milkItem = new InventoryItem(milkPacket, 50, 20, 10, 10, 25.0);
        InventoryItem appleItem = new InventoryItem(wallPaint, 100, 50, 30, 20, 10.0);

        store.addInventoryItem(milkItem);
        store.addInventoryItem(appleItem);

        Cart cart1 = new Cart("CART001", "In-store");
        cart1.addItem(milkPacket, 2);
        cart1.addItem(wallPaint, 5);

        store.addCart(cart1);

        store.checkOutCart(cart1);

        store.listProductsByCategory(Category.DAIRY);
    }
}

