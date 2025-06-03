package samplecodes;
import java.util.*;
import java.util.stream.Collectors;
public class StreamsPractice {
	public static void main(String[] args) {
		List<Integer> l=new ArrayList<>();
		
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		l.add(6);
		
		List<Integer> res=new ArrayList<>();
		l.stream()
				.filter(n->n%2==0)
				.map(n->n*3)
				.forEach(x -> System.out.println(x));
		//System.out.println(res);
	}
	
}
