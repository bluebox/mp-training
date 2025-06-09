package com.dailybasics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsBaiscs {
	public static void main(String[] args) {
		Random random = new Random();
		Integer[] nums = new Integer[1000];
		for (int i = 0; i < 1000; i++) {
			nums[i] = random.nextInt(1000);
		}
		System.out.println("All random integers : " + Arrays.toString(nums));
		Map<Integer, Integer> numsValues = List.of(nums).stream().collect(
				Collectors.groupingBy(n -> n, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
		System.out.println(numsValues);
		// finding palindromes in a list
		List<String> names = Arrays.asList("Saketh", "NaN", "Anil", "Aravind","vara prasad");
		List<String> Palindromes = names.stream().filter(n -> n.equals(new StringBuilder(n).reverse().toString()))
				.collect(Collectors.toList());
		System.out.println(Palindromes);
		// finding first Element that divisible b both 5 and 7
		Optional<Integer> value = Arrays.asList(nums).stream().filter(n -> (n % 5 == 0) && (n % 7 == 0)).findFirst();
		System.out.println(value);
		//Employees who's salary is above 50000
		List<Employee> above50000 = new EmployeeList().employees.stream()
				.filter(n -> n.salary>50000)
				.collect(Collectors.toList());
		System.out.println(above50000);
		//names with more than or equal to 3 vowels
		List<String> namesWithVowels = names.stream()
				.filter(n -> n.replaceAll("[^aeiouAEIOU]","").length()>=3)
				.collect(Collectors.toList());
		System.out.println(namesWithVowels);
		//names with N
		Optional<String> nameStart = names.stream()
				.filter(s->s.toLowerCase().startsWith("n"))
				.findFirst();
		System.out.println("nameStart : "+nameStart);
		//perfect squareroots
		Arrays.asList(nums).stream()
		.filter(n->Math.sqrt(n)%1==0)
		.distinct()
		.forEach(System.out::println);
		//strings that have length more than 10
		names.stream()
		.filter(n->n.length()>=10)
		.forEach(System.out::println);
		//converting ints to strings
		List<String> intToStrings = Arrays.asList(nums).stream()
				.map(String::valueOf)
				.collect(Collectors.toList());
		intToStrings.forEach(s->System.out.print(s+"\t"));
		System.out.println();
		//mapping emplyee to employee id
		new EmployeeList().employees.stream()
		.map(Employee::getid)
		.forEach(n->System.out.print(n+"\t"));
		System.out.println();
		//list of strings into there lengths
		List<Integer> stringsLength = names.stream()
				.map(String::length)
				.collect(Collectors.toList());
		stringsLength.forEach(n->System.out.print(n+"\t"));
		System.out.println();
		//mapping id + salary
		new EmployeeList().employees.stream()
		.map(n->n.id + " "+n.salary)
		.forEach(n->System.out.print(n+"\t"));
		System.out.println();
		//firstletters of strings
		names.stream()
		.map(s->s.charAt(0))
		.forEach(n->System.out.print(n+"\t"));
		System.out.println();
		//multiplying salary with id
		new EmployeeList().employees.stream()
		.map(n->n.id*n.salary)
		.forEach(n->System.out.print(n+"\t"));
		System.out.println();
		//integers to  its ascii values
		Arrays.asList(nums).stream()
		.map(n->Integer.toBinaryString(n))
		.forEach(n->System.out.print(n+"\t"));
		System.out.println();
		//mapping emp salary to string
		new EmployeeList().employees.stream()
		.map(n->String.valueOf(n.salary))
		.forEach(n->System.out.print(n+"\t"));
		System.out.println();
		//reversing names
		names.stream()
		.map(n->new StringBuilder(n).reverse().toString())
		.forEach(n->System.out.print(n+"\t"));
		System.out.println();
		//Grouping by their salary
		Map<Integer,List<Employee>> sameSalary = new EmployeeList().employees.stream()
				.collect(Collectors.groupingBy(Employee::getSalary));
		sameSalary.forEach((n,v)-> System.out.println(n +" "+v));
		//procduct of all +ve integers
		Long product = Arrays.asList(nums).stream()
		.filter(n->n>0)
		.mapToLong(n->n)
		.limit(10)
		.reduce(1L,(a,b)->a*b);
		System.out.println("product of numbers : "+product);
		//count of each occurance
		Map<Integer,Long> countToValue = Arrays.asList(nums).stream()
				.collect(Collectors.groupingBy(n->n,Collectors.counting()));
		System.out.println(countToValue);
		//groupingby and addinging ids
		Map<Integer,Long> sumOfIds = new EmployeeList().employees.stream()
				.collect(Collectors.groupingBy(n->n.salary,Collectors.summingLong(n->n.id)));
		System.out.println(sumOfIds);
		
		
		
	}
	//Creating employee list
	static class EmployeeList {
		List<Employee> employees = new ArrayList<>();
		Random random = new Random();

		public EmployeeList() {
			for (int i = 0; i < 1000; i++) {
				employees.add(new Employee(i, random.nextInt(50000)+30000));
			}
		}
	}
	//creating a class employee for object
	static class Employee {
		int id;
		int salary;

		public Employee(int id, int salary) {
			this.id = id;
			this.salary = salary;
			
		}
		public int getid() {
			return id;
		}
		public int getSalary() {
			return salary;
		}
		public String toString() {
			return "id : %d , salary : %d".formatted(id,salary);
		}
	}
}
