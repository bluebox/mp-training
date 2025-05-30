package Sets;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetExample {

	public static void main(String[] args) {
		LinkedHashSet<Integer>st=new LinkedHashSet<>();
		//Set<Integer> st=new LinkedHashSet<>(); is also possible
		st.add(50);
		st.add(60);
		st.add(30);
		// this maintain the order of insertion
		for(Integer ele:st) {
			System.out.print(ele+" ");
		}
		System.out.println();
		st.remove(50);
		for(Integer ele:st) {
			System.out.print(ele+" ");
		}
		System.out.println();
		System.out.println(st.size());
		st.clear();
		System.out.println(st.size());
	}

}
