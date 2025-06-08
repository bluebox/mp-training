package collections;
import java.util.LinkedList;
public class LinkedListInterface {

	public static void main(String[] args) {
		LinkedList<String> fruits = new LinkedList<String>();
		fruits.add("Banana");
		fruits.add("Custard Apple");
		fruits.add("Guava");
		fruits.addFirst("Apple");
		fruits.addLast("Papaya");
		System.out.println(fruits);
		System.out.println(fruits.size());
		System.out.println(fruits.contains("Orange"));
		System.out.println(fruits.getFirst());
		System.out.println(fruits.getLast());
		System.out.println(fruits.get(2));
		fruits.remove("Guava");
		System.out.println(fruits);
		fruits.removeFirst();
		System.out.println(fruits);
		fruits.removeLast();
		System.out.println(fruits);
		fruits.remove(0);
		System.out.println(fruits);
		
		fruits.offer("Grapes");
		System.out.println(fruits);		
		System.out.println(fruits.peek());
		System.out.println(fruits);
		System.out.println(fruits.poll());
		System.out.println(fruits);
		
		fruits.push("Watermelon");
		System.out.println(fruits);
		System.out.println(fruits.pop());
		System.out.println(fruits);
		
		fruits.clear();
		System.out.println(fruits);
		System.out.println(fruits.isEmpty());
	}

}
