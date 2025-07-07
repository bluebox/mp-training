import java.util.ArrayList;
import java.util.List;
public class Store {
    private List<Product> productList;
    public Store() 
    {
        productList=new ArrayList<>();
    }
    public void addProduct(Product product){
        productList.add(product);
    }
    public void showProducts(){
        System.out.println("Available Products:");
        for (Product p:productList){
            System.out.println(p);
        }
    }
    public Product getProductById(int id)
    {
        for(Product p : productList){
            if(p.getId()==id) 
            {
                return p;
            }
        }
        return null;
    }
}