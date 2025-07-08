package Store;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

public class Store {
  //  private static final String LocalDate = null;
	public Map<Product,InventoryItem> Inventory=new HashMap<>();
   public  List<Cart> carts=new ArrayList<>();
    public Map<Product,InventoryItem> AisleInventory=new HashMap<>(); // for getting the items in stores shelves
    
   public void manageStoreCarts() {
	   // write setqtyreserved,type physical or virtual
	   for(Cart cart:carts) {
	   for(Product product:cart.products) {
	        	 int value1= Inventory.get(product).getTotalQty();
	        	  if(cart.typ==type.Virtual) {
	        		  int value2=Inventory.get(product).getQtyReserved();
	        		  Inventory.get(product).setTotalQty(value1-value2);
	        		  AisleInventory.put(product,  Inventory.get(product));
	        	  }
	          }  
	   }
	   
	   
   }
   
   public void checkoutCart(Cart cart) {
		   for(Product product:cart.products) {
			  if((Inventory.get(product).getTotalQty()-Inventory.get(product).getQtyReserved())<=Inventory.get(product).getQtyLow()) {
				  Inventory.get(product).setQtyReorder(100);
			  }
			System.out.println(product.name+" is product of the cart - "+cart.getId()) ; 
		   }
		   
	   
   }
   
   public void abandonCarts() {// abandon the cart if the date is not current date
	   LocalDate today = LocalDate.now();
	   ListIterator<Cart> iter=carts.listIterator(); 
	   for(Cart cart:carts) {
		   long daysBetween = ChronoUnit.DAYS.between(cart.Date, today);
		   if(daysBetween > 7) {
			   iter.remove();
		   }
		   iter.next();
	   }
   }
   
   public void listProductsbycategory() {
	   for(Entry<Product, InventoryItem> proddetails:Inventory.entrySet()) {
		  System.out.println("Name of Product : "+proddetails.getValue().getProduct().name+"Name of the Category : "+ proddetails.getKey().category);
	   }
   }
   
   public static void main(String[] args) {
	
}
    
    
}
