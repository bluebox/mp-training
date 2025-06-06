package StoreInventorySystem_CollectionsFrameworkChallenge_In_Progress;
import java.util.List;
import java.util.Map;

import StoreInventorySystem_CollectionsFrameworkChallenge_In_Progress.Category;
public class Store {
	private Map<String, InventoryItem> inventory;
	private List<Cart> carts;
	private Map<Category,List<InventoryItem> > aisleInventory;
}
