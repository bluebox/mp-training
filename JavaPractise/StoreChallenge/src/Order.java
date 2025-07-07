import java.util.ArrayList;
import java.util.List;
public class Order {
    private List<Product> orderedItems;
    public Order() 
    {
        orderedItems=new ArrayList<>();
    }
    public void addProduct(Product product) 
    {
        orderedItems.add(product);
    }
    public void printOrder() {
        System.out.println("Ordered Products:");
        for (Product product : orderedItems) {
            System.out.println(product);
        }
    }
}