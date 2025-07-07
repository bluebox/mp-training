import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Store store=new Store();
        Order order=new Order();
        Scanner sc=new Scanner(System.in);
        store.addProduct(new Product(1,"Soap",25));
        store.addProduct(new Product(2,"Shampoo",120));
        store.addProduct(new Product(3,"Toothpaste",50));
        store.showProducts();
        while (true) {
            System.out.print("Enter product ID to order (0 to finish): ");
            int id=sc.nextInt();
            if(id==0) break;
            Product product=store.getProductById(id);
            if(product != null){
                order.addProduct(product);
                System.out.println(product.getName()+" added to order.");
            } 
            else
            {
                System.out.println("Product not found.");
            }
        }
        System.out.println();
        order.printOrder();
    }
}