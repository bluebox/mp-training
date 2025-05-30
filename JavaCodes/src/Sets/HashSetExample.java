package Sets;

import java.util.*;

public class HashSetExample {

	public static void main(String[] args) {
		Set<Integer> st=new HashSet<>();
		st.add(30);
		st.add(60);
		st.add(70);
		if(st.contains(70)) {
			System.out.println(70 + " is already exist");
		}
		// other way
		if(!st.add(70)) {
			System.out.println(70+" is already exist by add method");
		}
		// remove method set before remove
		for(Integer ele:st)System.out.print(ele+" ");
		System.out.println();
		st.remove(70);
		// after remove
		for(Integer ele:st)System.out.print(ele+" ");
		System.out.println();
		st.removeAll(st);
		System.out.println(st.size());
	}

}
