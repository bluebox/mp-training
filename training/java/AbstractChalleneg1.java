import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store{
    public static void main(String [] args){
        Scanner scanner =new Scanner(System.in);
        
        ArrayList<ProductForSale> productsList = new ArrayList<>();
        ArrayList<ProductForSale> ordersList = new ArrayList<>();

        productsList.addAll(List.of(
        new ProductForSale("fish", 3.76, "it is a sea food"),
        new ProductForSale("chicken 65", 4.78, "it is a good food"),
        new ProductForSale("muton", 9.08, "it's costly but delicious"),
        new ProductForSale("brinjal fry", 1.35, "it's a pure veg item")));
        printMenu(productsList);
        System.out.println("ebter 'Q' to quit");
        System.out.println("enter 'END' to end the your order");
        System.out.println("enter the name of the item to add ");
        boolean Q=true;
        while(Q){
            String input = scanner.nextLine();
            switch(input){
                case "Q","q":System.out.println("Thank you visit again!!");Q=false;break;
                case "END","end":showDetails(ordersList);Q=false;break;
                default:
                for(ProductForSale item:productsList){
                    if(item.type.equals(input)){
                        ordersList.add(item);
                        System.out.println("enter the next item");
                        break;
                    }
                    else{
                        System.out.println("the item "+input+" is not present!! please try with others");
                    break;
                    }
                }
            }
        }
        
            
        }
       // System.out.println(productsList);




    public static void showDetails(ArrayList<ProductForSale> ordersList){
        double billAmount=0;
        for(ProductForSale item:ordersList){
            billAmount+=item.price;
        }
        System.out.println("your order details are as follows:");
        ordersList.forEach(System.out::println);
        System.out.println("Total Amount=$"+billAmount+" /- only");
       }
    
    public static void printMenu(ArrayList<ProductForSale> productsList){
        System.out.println("the available items are as follows:");
        System.out.println("-".repeat(50));
        productsList.forEach(System.out::println);
    }


}
    public abstract class ProductItemsForSale{
    String type;
    double price;
    String description;
    public ProductItemsForSale(String type,
    double price,
    String description){
        this.type=type;
        this.price=price;
        this.description=description;
    }}

    public class ProductForSale extends ProductItemsForSale{
    public ProductForSale(String type,
    double price,
    String description){
        super( type,price,description);
    }
    public String toString(){
        System.out.println(type+":");
        System.out.println("Price ::$"+price+"/-");
        System.out.println(description);
        System.out.print("-".repeat(50));

        

    return "";

    }
    
}
