package streams;

import java.util.ArrayList;
import java.util.List;

public class ArrayListStream {
	public static void main(String[] args) {
		ArrayList<Integer> lst=new ArrayList<>(List.of(45,67,47,39,12,31));
		System.out.println(lst.stream().allMatch((n)->n%3==0));
		System.out.println(lst.stream().anyMatch((n)->n%3==0));
		lst.stream().sorted().forEach(n->System.out.print(n+" "));
		
		System.out.println();
		lst.stream()
		.skip(2).sorted().forEach(n->System.out.print(n+" "));
		
	}

}
