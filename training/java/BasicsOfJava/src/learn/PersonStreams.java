package learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PersonStreams {

	public static void main(String[] args) {
		
		ArrayList<Person> list = new ArrayList<>();
		List<String> names = Arrays.asList("Dsp","Sri","Sai","Prasad","Vara");
		
		Random random = new Random();
		
		for(int i=0; i<5;i++) {
			
			list.add(new Person(names.get(i),random.nextInt(18, 25)));
		}
		list.stream()
			.map(Person::toString)
			.forEach(System.out::println);;
	}
}
