import java.util.Iterator;
import java.util.LinkedList;

public class Linked {

	public static void main(String[] args) {
		
		LinkedList<Integer> link = new LinkedList<>();
		link.add(10);
		link.add(20);
		link.add(30);
		link.add(40);
		link.add(50);
		link.add(40);
		
		System.out.println(link);

		link.addFirst(100);
		link.addLast(200);
		System.out.println(link);
		
		link.offer(101);
		link.offerFirst(102);
		link.offerLast(103);
		System.out.println(link);
		
		
		link.push(200);
		System.out.println(link);
		link.pop();
		System.out.println(link);
		
		link.remove(0);
		System.out.println(link);
		link.remove();
		System.out.println(link);
		link.removeFirst();
		System.out.println(link);

		link.removeLast();
		System.out.println(link);
		
		System.out.println(link.get(5));
		System.out.println(link.getFirst());
		System.out.println(link.getLast());
		System.out.println(link);

		System.out.println(link.peek());
		System.out.println(link);

		System.out.println(link.peekFirst());
		System.out.println(link);

		System.out.println(link.peekLast());
		System.out.println(link);

		System.out.println(link.size());
		System.out.println(link);

		System.out.println(link.indexOf(200));
		System.out.println(link);

		System.out.println(link.element());
		
		Iterator<Integer> itr = link.iterator();
		
		while(itr.hasNext()) {
			if(itr.next().equals(101)) {
				itr.remove();
			}else
			System.out.print(itr.next() + " ");
		}





		

	}

}
