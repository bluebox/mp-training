package com.example.spring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring.model.Person;


@Controller
public class DynamicData {
	
	@GetMapping("/data")
	public String data(Model model) {
		List<Person> persons = Arrays.asList(
		new Person("Karthik", 21 , Person.State.AP),
		new Person("A", 20 , Person.State.TS),
		new Person("B", 19 , Person.State.TN),
		new Person("C", 18 , Person.State.AP),
		new Person("D", 17 , Person.State.TS));
		
//		Person.State[] states = Person.State.values();
//		
//		for(State s: states) {
//			model.addAttribute(s.toString(), persons.stream().filter(p->p.getAddress().equals(s)).collect(Collectors.toList()));
//		}
		
		model.addAttribute("person", persons);

		
		
		return "data.html";
	}

}
