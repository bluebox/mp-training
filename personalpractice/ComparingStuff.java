package dev.tulasidhar.personalpractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class ComparingStuff {
	public static void main(String[] args) {
		List<Animal> animals = List.of(new Animal("Saketh"), new Animal("Kaushik"),new Animal("Vardhan"));
		List<Animal> animals2 = new ArrayList<>(animals);	
		List<String> strings = new ArrayList<String>();
		strings.add("Hello");
		strings.add("Pokemon");
		strings.add("Apple");
		strings.add("Nuts");
		
		Collections.sort(strings);
//		Collections.sort(animals2,
//					(a1, a2)->
//						a1.name.compareTo(a2.name)
//					);
		Collections.sort(animals2);
				
		
		for(Animal animal:animals2) {
			System.out.println(animal.name);
		}
	}
}


class AnimalComparator implements Comparator<Animal>{
		@Override
		public int compare(Animal a1,Animal a2) {
			return a1.name.compareTo(a2.name);
			
		}
}

class Animal implements Comparable<Animal>{
	String name;
	
	public Animal(String name) {
		this.name = name;	
	}

	@Override
	public int compareTo(Animal o) {
		return this.name.compareTo(o.name);
		
	}
}