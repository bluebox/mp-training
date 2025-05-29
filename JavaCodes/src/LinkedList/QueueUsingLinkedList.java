package LinkedList;

import java.util.LinkedList;

public class QueueUsingLinkedList {

	public static void main(String[] args) {
		LinkedList<Integer>q=new LinkedList<>();
	    q.add(20);
	    q.add(40);
	    q.add(60);
	    System.out.println("q before pop");
	    for(Integer ele:q) {
	    	System.out.println(ele);
	    }
	    System.out.println("q after");
	    q.pop();
	    for(Integer ele:q) {
	    	System.out.println(ele);
	    }
//	    q before pop
//	    20
//	    40
//	    60
//	    q after
//	    40
//	    60
// this is the output where first 20 is delete that is LIFO
	}

}
