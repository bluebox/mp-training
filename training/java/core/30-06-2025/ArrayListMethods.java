import java.util.*;
public class ArrayListMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list=new ArrayList<>();
		for(int i=1;i<5;i++)
		{
			list.add(i);
		}
		System.out.println(list);
		list.remove(1);
		System.out.println(list);
		System.out.println(list.contains(1));
		list.add(1,5);
		System.out.println(list);
		list.set(2,90);
		System.out.println(list); 
		System.out.println(list.indexOf(1));
	    Collections.sort(list);
	    System.out.println(list);
	  
		

	}

}
