package collections;
import java.util.LinkedList;
import java.util.Queue;
public class QueueImplementation {
	public static void main(String[] args) {
	Queue<String> appliances = new LinkedList<String>();
	appliances.offer("TV");
	appliances.offer("AC");
	appliances.offer("FAN");
	appliances.offer("REFRIGERATOR");
	System.out.println(appliances);
	System.out.println(appliances.peek());
	System.out.println(appliances);
	System.out.println(appliances.poll());
	System.out.println(appliances);
	}
}
