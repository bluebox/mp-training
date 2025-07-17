package July17.Practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

class Person {
	private String name;
	private int age;

	public Person() {
		Random ran = new Random();
		this.name = ran.ints(97, 122 + 1).limit(7)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
	}

	public String getName() {
		return name;
	}
}

public class Main {
	public static <T> List<T> getObj(int length, Supplier<T> obj) {
		List<T> l = new ArrayList<T>();
		for (int i = 1; i <= length; i++) {
			l.add(obj.get());
		}
		return l;
	}

	public static void main(String[] args) {
		List<Person> p = getObj(7, Person::new);
		p.stream().map(x -> x.getName()).forEach(System.out::println);
	}
}