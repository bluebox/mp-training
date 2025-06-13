package LinkedList;

import java.util.Iterator;
import java.util.LinkedList;

public class StackUsingLinkedLIst {

	public static void main(String[] args) {
		 LinkedList<Integer>st=new LinkedList<>();
		 //push insert at starting of the list
		 //poll delete the start element of the list 
		 // which makes the LIFO 
		 st.push(10);
		 st.push(20);
		 st.push(30);
		 for(Integer ele:st) {
			 System.out.println(ele);
		 }
//		 30
//		 20
//		 10
		 System.out.println("after poll");
		 st.poll();
		 Iterator<Integer>it=st.iterator();
		 while(it.hasNext()) {
			 System.out.println(it.next());
		 }

	}

}
