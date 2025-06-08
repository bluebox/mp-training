package collections;
import java.util.PriorityQueue;
import java.util.Queue;
public class PriorityQueueInterface {
	public static void main(String[] args) {
	Queue<Integer> pQueue = new PriorityQueue<Integer>();
	pQueue.offer(30);
	pQueue.offer(10);
	pQueue.offer(20);
	pQueue.offer(5);
	System.out.println("PriorityQueue numbers (natural order): " + pQueue);
    System.out.println("Polling elements from PriorityQueue (natural order):");
    while (!pQueue.isEmpty()) {
        System.out.println("  Polling: " + pQueue.poll());
    }

    //using custom comparator to sort by lengths
    Queue<String> lenStringPriority = new PriorityQueue<>((s1, s2) -> s1.length() - s2.length());
    lenStringPriority.offer("Apple");
    lenStringPriority.offer("Pear");
    lenStringPriority.offer("Banana");
    lenStringPriority.offer("Kiwi");
    lenStringPriority.offer("Grapefruit");
    System.out.println("\nPriorityQueue strings : "+lenStringPriority);
    System.out.println("Polling elements from PriorityQueue (sorted by string length):");
    while (!lenStringPriority.isEmpty()) {
        System.out.println("  Polling: " + lenStringPriority.poll());
    }
	}
}
