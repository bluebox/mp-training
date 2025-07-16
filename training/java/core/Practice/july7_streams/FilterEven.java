package Practice.july7_streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterEven {

	public static void main(String[] args) {
		List<Integer> list=new ArrayList<>();
		Scanner sc=new Scanner(System.in);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		Stream<Integer> str=list.stream().filter((n)->n%2==0);
		List<Integer> evenList=str.collect(Collectors.toList());
		System.out.println(evenList);
		sc.close();
	}

}
