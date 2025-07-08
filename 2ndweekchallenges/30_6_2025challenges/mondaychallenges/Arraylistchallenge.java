
import java.util.*;
public class Arraylistchallenge {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ArrayList<String> itemlist = new ArrayList<String>();
       
        while (true) {
             System.out.println("0-to shutdown\n1-to add item(s) to list (comma delimited list)\n2-to remove any items ( comma delimited list) \n ");
        System.out.println("Enter the choice for which action you want to do:");
        
            int option=0;
            try{
             option=sc.nextInt();
            sc.nextLine();}
            catch(InputMismatchException i){
                System.out.println("please enter correct choice ");
                continue;
            }
            if(option==0){
                break;

            }
            else if(option==1){
                System.out.println("Enter the item ");
                String item=sc.nextLine();
                String[] arr_item=item.split(",");
                for(String items:arr_item){
                    items=items.trim();
                if(itemlist.contains(items)){
                    System.out.println(itemlist);
                    System.out.println("item already exits");
                    break;
                }
                itemlist.add(items);
                 }
                 
                Collections.sort(itemlist);
            System.out.println(itemlist); 

            }
            else if(option==2){

                System.out.println("Enter the item to remove");
                String items=sc.nextLine();
                String[] arr=items.split(",");
                for(String item:arr){
                    item=item.trim();
                    if(!itemlist.contains(item)){
                        System.out.println("item does not exist");
                        break;
                    }  
                    itemlist.remove(item);
                    System.out.println(itemlist);
                }
                
                
            }
            
                System.out.println(itemlist);
            }
            
        }
    }

