package July3;

import java.util.ArrayList;

public class Copy {
	public static void main(String[] args) {
		ArrayList<String> original = new ArrayList<>();
        original.add("A");
        original.add("B");

        // Shallow copy
        ArrayList<String> shallowCopy = (ArrayList<String>) original.clone();

        shallowCopy.set(0, "X"); 
        original.add("C");      

        System.out.println("Original: " + original); 
        System.out.println("Shallow Copy: " + shallowCopy); 
        
     // Deep copy
        ArrayList<String> deepCopy = new ArrayList<>(original);

        deepCopy.set(0, "X");  

        System.out.println("Original: " + original);
        System.out.println("Deep Copy: " + deepCopy); 
	}
}
