package com.example.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.model.Person;

public class Main {
	
	public static void main(String[] args) {
		List<Person> personlist = new ArrayList<>();
		personlist.add(new Person("saketh",33,44));
		personlist.add(new Person("sa",33,44));
		personlist.add(new Person("abc",33,44));
		
		
		Map<String,Person> mapping = personlist.stream()
				.collect(Collectors.toMap(n -> n.getName(), n -> n));
		
		System.out.println(personlist);
		
	}

}
