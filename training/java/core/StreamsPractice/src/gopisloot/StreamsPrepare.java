package gopisloot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsPrepare {
public static void main(String []args)
{
	List<Integer>al=new ArrayList<>();
	al.add(30);
	al.add(20);
	al.add(10);
	al.add(100);
	al.add(50);
	al.add(80);
	al.add(70);
	al.add(60);
	al.add(90);
	al.add(40);
	System.out.println("Original ArrayList: ");
	System.out.println(al);
	System.out.println("Using filter :");
	List<Integer>li=al.stream().filter(i->i>=50).collect(Collectors.toList());
	System.out.println(li);
	System.out.println("Using Map: ");
	List<Integer>ei=al.stream().map(i->i+6).collect(Collectors.toList());
	System.out.println(ei);
	System.out.println("Using sorted :");
	List<Integer>si=al.stream().sorted().collect(Collectors.toList());
	System.out.println(si);
	System.out.println("Using combination of intermediate operations :");
	List<Integer>ci=al.stream().map(i->i+6).filter(i->i>=50).sorted().collect(Collectors.toList());
	ci.forEach(System.out::println);
	
	
}
}
