package practice;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class EnumeratorExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Vector<String> l=new Vector<>();
//		l.add("Apple");
//		l.add("guva");
//		l.add("banana");
//		Enumeration<String> e=l.elements();
//		while(e.hasMoreElements())
//		{
//			System.out.println(e.nextElement());
//		}
		
//		Hashtable<Integer,String> mp=new Hashtable<>();
//		mp.put(1,"Apple");
//		mp.put(2, "Guva");
//		mp.put(3,"orange");
//		
//		Enumeration<String> e=mp.elements();
//		
//		while(e.hasMoreElements())
//		{
//			System.out.println(e.nextElement());
//		}
		
		
		Stack<String> st=new Stack<>();
		st.add("tarun");
		st.add("ravi");
		st.add("sai");
		Enumeration<String> e=st.elements();
		while(e.hasMoreElements())
		{
			System.out.println(e.nextElement());
		}
		
		

	}

}
