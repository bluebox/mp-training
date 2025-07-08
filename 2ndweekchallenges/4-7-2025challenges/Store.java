import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public class Store {
    private Map<Product,Inventoryitem> inventory;
    private List<Cart> carts;
    private Map<Product,Inventoryitem> aisleInventory;


    public void manageStoreCarts(){
        for(Cart c:carts){
            if(c.type==Type.PHYSICAL){
                aisleInventory.put(c.getProducts(),inventory.get(c.getProducts()));
            }
        }

    }
    public Cart checkOutCart(int id){

        for(Cart c:carts){
            if(c.getId()==id){
                return c;
            }

        }
        System.out.println("no cart found by that id");
        return null;

    }
    public void abondonCarts(){
        LocalDate today = LocalDate.now();

        for(Cart c:carts){
        long daysBetween = ChronoUnit.DAYS.between(c.date,today);

     }

   }
    public Product listProdcutsByCategory(String category){
        
        for(Map.Entry<Product,Inventoryitem> entry:aisleInventory.entrySet()){
            if(entry.getKey().getCategory().equals(category)){
                return entry.getKey();

            }
        }
        System.out.println("no porduct with such category");
        return null;

        
    }
}
