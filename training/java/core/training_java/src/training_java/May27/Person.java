package training_java.May27;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class Person {
	public static void main (String[] args) {
	Queue<Integer> dishes=new LinkedList<Integer>();
	Queue<Integer> persons=new LinkedList<Integer>();
	dishes.add(0);
	dishes.add(1);
	dishes.add(0);
	dishes.add(1);
	persons.add(1);
	persons.add(0);
	persons.add(0);
	persons.add(1);
	int count=0;
//	System.out.println("Enter size :");
//	Scanner sc=new Scanner(System.in);
//	int size=sc.nextInt();
	while(dishes.size()>0 && persons.size()>0 && count<persons.size()) {
		if(dishes.peek()==persons.peek()) {
			dishes.poll();
			persons.poll();
			count=0;
		}
		else {
			int p=persons.poll();
			persons.offer(p);
			count+=1;
		}
		
	}
	System.out.println(persons.size());
	}

}
