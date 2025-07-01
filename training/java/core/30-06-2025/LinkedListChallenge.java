import java.util.*;
public class LinkedListChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> list=new LinkedList<>();
		list.add("Apple");
		list.add("Banana");
		System.out.println("List is "+list);
		list.addFirst("orange");
		list.addLast("Papaya");
		System.out.println("list is"+list);
		list.remove();
		System.out.println("list is"+list);
		list.removeFirst();
		System.out.println("list is"+list);
		list.removeLast();
		System.out.println("list is"+list);
		list.add("Tarun");
		list.add("ravi");
		list.add("raju");
		list.add("ranga");
		System.out.println("List is"+list);
		System.out.println(list.get(1));
		list.set(3,"pallavi");
		System.out.println("list is"+list);
		
		

	}

}
