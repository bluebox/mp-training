package Collections;
import java.util.*;

public class LinkedListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> states=new LinkedList<>();
		
		states.add("Andra Pradesh");
		states.add("Telangana");
		states.add("Karnataka");
		
		System.out.println("States : "+states);
		
		states.add(2, "Tamil nadu");
		
		states.addFirst("Delhi");
		
		states.addLast("Punjab");
		
		System.out.println("States : "+states);
		
		System.out.println("Punjab is there ? : "+states.contains("Punjab"));
		
		System.out.println("size/length of states list : "+states.size());
		
		states.set(3, "Maharastra");
		
		
		System.out.println("Index of Telanagana : "+states.indexOf("Telangana"));
		
		System.out.println("First state in list : "+states.getFirst());
		
		System.out.println("Last state in list : "+states.getLast());
		
		states.offer("Meghalaya");
//		offerLast is also same i.e it add element at end of the list
		
		states.offerFirst("Kerala");

		
		System.out.println("Peek of the list : "+states.peek());
		
		System.out.println("States : "+states);
		
		states.remove();
		
		states.remove(5);
		
		states.remove("Karnataka");
		
		System.out.println("After remove opration States : "+states);
		
		LinkedList<String> states2=new LinkedList<String>();
		
		states2.add("Uttar Pradesh");
		states2.add("Nagaland");
		states2.add("Madhya Pradesh");
		
		System.out.println("List 2 elemnets : "+states2);
		
		states.addAll(3, states2);
		
		System.out.println("After list 2 merged with list 1 : "+ states);
		
		String s1=states.poll();
		
		System.out.println("First Removed state is : "+s1);
		
		String s2=states.pollLast();
		
		System.out.println("Last removed state is : "+s2);

		System.out.println("Reverse order of states : ");
		
		Iterator<String> itr=states.descendingIterator();
		
		while(itr.hasNext()) {
			System.out.println("    "+itr.next());
		}


	}

}
