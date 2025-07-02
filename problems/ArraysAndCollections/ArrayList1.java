  package ArraysAndCollections;
import java.util.*;
 
 
public class ArrayList1 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ArrayList<String> Itemslist=new ArrayList<>();
		
		while(true) {
			System.out.println("Enter 0 to Shut Down \n enter 1 to add item(s) to list (comma delimited List) \n enter 2 to remove items(comma delimited list) ");
	        System.out.println("Enter the number corresponding to Action of your Choice");
	        
	        int option=1;
	        try {
	        	 option=sc.nextInt();
	        }catch(InputMismatchException e) {
	        	System.out.println("Enter valid input ");
	        	continue;
	        }
	        
	        if(option==0) {
	        	break;
	        }
	        
	        else if(option==1) {
	        	System.out.println("Enter the item");
	        	String item=sc.next();
	        	String arr[]=item.split(",");
	        	for(String item_:arr) {
	        	if(Itemslist.contains(item_)) {
	        		System.out.println("Item already Exists");
	                break;
	        	}
	        	Itemslist.add(item_);
	        	}
	        	Collections.sort(Itemslist);
	        	System.out.println(Itemslist);
	        }
	        
	        else if(option==2) {
	        	System.out.println("Enter the item");
	        	String item=sc.next();
	        	String arr[]=item.split(",");
	        	for(String item_:arr) {
	        	if(!Itemslist.contains(item_)) {
	        		System.out.println("Item does not Exists");
	        		break;
	        	}
	        	Itemslist.remove(item_);
	        	System.out.println(Itemslist);
	        	}
	        }
	        
	        }
	        
	        
	        
	}
}
