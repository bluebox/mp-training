package Arrays;
import java.util.*;

public class ArrayListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		        ArrayList<String> list = new ArrayList<>();
		      
		        list.add("Jai");
		        list.add("Ganesh");
		        list.add("Sairaj");
		        list.add("Jagan");

		      	System.out.println("Orignlist List : "+list);
		      	
		      	System.out.println("Size / Length of the list : "+list.size());
		      
		        list.add(2, "Mahesh");

		      	System.out.println("After Adding element at index 2 : "+ list);
		      	
		      	list.remove(1);
		      
		      	System.out.println("Element removed from index 1 : "+ list);
		      	
		      	list.remove("Jagan");
		      
		      	System.out.println("Element Jagan removed : "+ list);
		      
		      	list.set(0, "Harish");
		      	
		      	System.out.println("List now : "+list);
		      	
		      	System.out.println("Jai is there in the list : "+list.contains("Jai"));
		      	
		      	System.out.println("The list empty now : "+list.isEmpty());
		      	
		      
	}

}
