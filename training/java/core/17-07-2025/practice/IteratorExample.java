package practice;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Iterator;

public class IteratorExample {
	public static void main(String args[])
	{
//		ArrayList<Integer> a1=new ArrayList<>();
//		a1.add(1);
//		a1.add(2);
//		a1.add(3);
//		Iterator<Integer> i=a1.iterator();
//		while(i.hasNext())
//		{
//			System.out.println(i.next());
//		}
		
		HashMap<Integer,String> mp=new HashMap<>();
		mp.put(1,"Apple");
		mp.put(2, "Guva");
		mp.put(3,"orange");
		
		mp.keySet().iterator();
		mp.values().iterator();
		
	}

}
