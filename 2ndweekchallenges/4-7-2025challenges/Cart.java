import java.util.List;
import java.time.LocalDate;
enum Type{
    VIRTUAL,
    PHYSICAL
}
public class Cart {

    private int id;
    private Product products;
    private LocalDate date=LocalDate.now();
    private int price;
     Type type;
    private List<Cart> li;
      
    public Cart(int id, Product products, LocalDate date, Type type,int price) {
        this.id = id;
        this.products = products;
        this.date = date;
        this.type = type;
        this.price=price;
    }
    void additem(Cart c){
        li.add(c);
    }
    void removeitem(int id){
        for(Cart c:li){
            if(c.id==id){
                li.remove(c);
            }
        }

    }
    void printSalesSlip(){
        for(Cart c:li)
        System.out.println("product "+c.products+"price "+c.price);
    }
    public LocalDate getDate() {
        return date;
    }
    public int getId() {
        return id;
    }
    public Product getProducts() {
        return products;
    }


}
