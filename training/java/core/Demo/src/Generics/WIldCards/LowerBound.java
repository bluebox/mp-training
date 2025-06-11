package Generics.WIldCards;
import java.util.ArrayList;
import java.util.List;

public class LowerBound {
	
	public static void addNumbersToList(List<? super Integer>list) {
		
		list.add(12);
		list.add(14);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> intList = new ArrayList<>();
	    addNumbersToList(intList); 
	    System.out.println("Integer List: " + intList); 

	    List<Number> numList = new ArrayList<>();
	    addNumbersToList(numList); 
	    System.out.println("Number List: " + numList); 

	    List<Object> objList = new ArrayList<>();
	    addNumbersToList(objList); 
	    System.out.println("Object List: " + objList); 

	}

}
