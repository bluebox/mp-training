package Sets;

import java.util.*;
// Diff between NavigableSet and normal Set is we have extra method like floor and ceiling in Navigable which simpler to find the elements
// Diff between normal Hashset and TreeSet is TreeSet maintain order
public class TreeSetExample {

	public static void main(String[] args) {
        NavigableSet<Integer>tree=new TreeSet<>();
        tree.add(20);
        tree.add(40);
        tree.add(50);
        System.out.println(tree.floor(30));
        System.out.println(tree.ceiling(70));
        Set<Integer>a=new TreeSet<>();
        a.add(50);
        for(Integer ele:tree) {
        	System.out.print(ele+" ");
        }
        System.out.println();
        Iterator<Integer>it =tree.iterator();
        while(it.hasNext()) {
        	System.out.print(it.next()+" ");
        }
	}

}
