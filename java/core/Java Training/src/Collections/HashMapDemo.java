package Collections;
import java.util.*;

public class HashMapDemo {
	
	public static void main(String args[])
    {
        HashMap<Integer, String> emp = new HashMap<>();

                HashMap<Integer, String> hm2 = new HashMap<Integer, String>();

        emp.put(1, "Mahesh");
        emp.put(2, "Jagadheesh");
        emp.put(3, "Tharun");
        emp.put(4, "Jagan");
        
        System.out.println("Mappings of HashMap emp are : "+ emp);
        
        System.out.println("Size of the employees hashmap : "+ emp.size());
        
        System.out.println("Tharun is in the list : "+emp.containsValue("Tharun"));
        
        System.out.println("Employee list empty now : "+emp.isEmpty());
        
        String removedName = emp.remove(2);
        
        System.out.println("Removed Employee name is : "+removedName);
        
        System.out.println("Employee ID     |    Employee Name");
        
        emp.forEach((key, value) -> System.out.println("	"+key + "	:	" + value));
        
    }
}

