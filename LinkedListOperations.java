
import java.util.LinkedList;

public class LinkedListOperations {
    public static void main(String[] args) {
        LinkedList<String> queue = new LinkedList<>();

        queue.add("Monday");
        queue.add("Tuesday");
        queue.add("Wednesday");

        queue.addFirst("Sunday");     // Add at front
        queue.addLast("Thursday");    // Add at end

        System.out.println("Days: " + queue);

        queue.removeFirst();          // Removes "Sunday"
        queue.removeLast();           // Removes "Thursday"

        System.out.println("After remove: " + queue);
    }
}

