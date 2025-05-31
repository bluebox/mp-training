package linkedlist;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class LinkedListExample {
	public static void main(String[] args) {
		
		LinkedList<Integer> list=new LinkedList<>(List.of(2,3,4,5,6));
		list.add(45);
		System.out.println(list);
		list.remove(Integer.valueOf(6));
		System.out.println(list);
		
		list.addAll(List.of(11,12,13,14,11));
		System.out.println(list);
		
		//Ascending Order
		list.sort((a,b)->a-b);
		System.out.println(list);
		
		//decending order
		list.sort((a,b)->b-a);
		System.out.println(list);
		
		System.out.println(list.removeFirst());
		System.out.println(list.removeLast());
		System.out.println(list);
		System.out.println(list.pop());
		System.out.println(list);
		list.forEach((a)->{
			System.out.print(a+" ");
		});
		System.out.println();
		//List With Objects
		List<Students> students=new LinkedList<>(); 
		Students stu=new Students(4,"sai");
		students.add(stu);
		stu=new Students(4,"mani");
		students.add(stu);
		stu=new Students(1,"chotu");
		students.add(stu);
		stu=new Students(3,"fire");
		students.add(stu);
		stu=new Students(2,"sunny");
		students.add(stu);
		stu=new Students(5,"bunny");
		students.add(stu);
		System.out.println(students);
		Collections.sort(students);
		System.out.println(students);
		
	}

}
