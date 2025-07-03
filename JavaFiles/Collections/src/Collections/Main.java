package Collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Collection<String> list = new HashSet();
		
		String[] words= {"Hello","Hi","Morining","Bye"};
		
		list.addAll(Arrays.asList(words));
		
		System.out.println(list);
		
		list.add("App");
		System.out.println(list);
//		list.add("App");
//		System.out.println(list);
		
//		list.add(null);
//		list.add(null);
//		System.out.println(list);


		list.addAll(Arrays.asList("App","Apple","Azure"));
		
		System.out.println(list);
		
		System.out.println(list.contains("Apple"));
		
		list.removeIf(s -> s.charAt(0)=='A');
		
		System.out.println(list);



		
	}

}
