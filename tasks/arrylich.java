import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
public class arrylich {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ArrayList<String> groceries = new ArrayList<>(Arrays.asList("apple","sugar","milk","salt","chocolate"));
        System.out.println(groceries);
        boolean b=true;
        
        


        
        while (b){
            
            
            System.out.println("Available actions:");
        System.out.println("0 to shutdown");
        System.out.println("1 to add item to list");
        System.out.println("2 to remove any items");

        int option=sc.nextInt();
        

        
        
        
        switch (option) {
            case 1:
            System.out.println("enter item ");
            String item=sc.next();
            
            System.out.println(additem(item,groceries));
            break;
                
            
            case 2:
            System.out.println("enter item ");
            String item1=sc.next();
            
            System.out.println(removeitem(item1,groceries));
            break;
            
            case 0:
            b=false;
            System.out.println("bye.... and thank you");
            sc.close();

           
        }
        
    }
}
    public static ArrayList<String> additem(String item,ArrayList<String> groceries){
        if(!(groceries.contains(item))){
            groceries.add(item);

        }
        Collections.sort(groceries);
        return groceries;
        
    }
    public static ArrayList<String> removeitem(String item,ArrayList<String> groceries){
        if(groceries.contains(item)){
            groceries.remove(item);
            
             
        }
        else{
            System.out.println("item is not presented....");

        }
        Collections.sort(groceries);
        return groceries;
    }

}
