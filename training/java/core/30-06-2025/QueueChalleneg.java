import java.util.*;
public class QueueChalleneg {
	public static void main(String args[])
	{
		Queue<String> q=new LinkedList<>();
		q.add("Tarun");
		q.add("ram");
		q.add("Jagadeesh");
		q.add("Ravi");
		System.out.println("Data in the queue is "+q);
		System.out.println("Popped element is"+q.poll());
		System.out.println("Top element is "+q.peek());
		q.offer("Ramana");
		System.out.println("Data in the queue is "+q);
		q.remove();
		System.out.println("Data in the queue is "+q);
		
	}
	
	

}
