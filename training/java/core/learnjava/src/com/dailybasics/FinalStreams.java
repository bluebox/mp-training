package com.dailybasics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class FinalStreams {
	class EmployeeAttributes {
		private int id;
		private String name;
		private int age;
		private String department;
		private double salary;
		private List<String> projects;

		public EmployeeAttributes(int id, String name, int age, String department, double salary,
				List<String> projects) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
			this.department = department;
			this.salary = salary;
			this.projects = projects;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public String getDepartment() {
			return department;
		}

		public double getSalary() {
			return salary;
		}

		public List<String> getProjects() {
			return projects;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		public void setProjects(List<String> projects) {
			this.projects = projects;
		}
		public String toString(){
			return " id : %d Name : %s Age : %d dept : %s salary : %.2f Projects : %s".formatted(id,name,age,department,salary,projects);
		}

	}
	class EmployeeAtriList {
		private List<EmployeeAttributes>  listOfEmployees = new ArrayList<>();;
		public EmployeeAtriList() {
			listOfEmployees.addAll(Arrays.asList(
					new EmployeeAttributes(56165,"Saketh",23,"development",43584.00,Arrays.asList("Java","Angular")),
					new EmployeeAttributes(56165,"Anil",40,"Management",41232.00,Arrays.asList("Java")),
					new EmployeeAttributes(56165,"Minnu",31,"DataBase",58191.00,Arrays.asList("Java","SpingBoot")),
					new EmployeeAttributes(56165,"Sai",27,"development",98195.00,Arrays.asList("Java","React")),
					new EmployeeAttributes(56165,"Bhushan",24,"DataBase",51561.00,Arrays.asList("Java","Angular")),
					new EmployeeAttributes(56165,"Anjan",21,"Management",87657.00,Arrays.asList("Java","Angular")),
					new EmployeeAttributes(56165,"Pavan",42,"development",27272.00,Arrays.asList("React","Angular")),
					new EmployeeAttributes(56165,"Virat",41,"DataBase",72272.00,Arrays.asList("SpingBoot","Angular")),
					new EmployeeAttributes(56165,"Messi",47,"DataBase",25247.00,Arrays.asList("Java","Angular")),
					new EmployeeAttributes(56165,"Naruto",53,"development",14254.00,Arrays.asList("Java","React")),
					new EmployeeAttributes(56165,"Luffy",34,"Management",43584.00,Arrays.asList("React","Angular")),
					new EmployeeAttributes(56165,"Zoro",37,"DataBase",25732.00,Arrays.asList("SpingBoot","Angular")),
					new EmployeeAttributes(56165,"Sanji",32,"development",51561.00,Arrays.asList("Java","React")),
					new EmployeeAttributes(56165,"Nami",23,"DataBase",27852.00,Arrays.asList("Java","Angular")),
					new EmployeeAttributes(56165,"Brook",208,"Management",27227.00,Arrays.asList("SpingBoot","Angular")),
					new EmployeeAttributes(56165,"Chopper",13,"development",12752.00,Arrays.asList("Java","SpingBoot")),
					new EmployeeAttributes(56165,"usoop",38,"DataBase",27582.00,Arrays.asList("SpingBoot","Angular"))
					));
		}
		public List<EmployeeAttributes> getList(){
			return listOfEmployees;
		}
	}
	public static void main(String[] args) {
		FinalStreams outer = new FinalStreams();
		FinalStreams.EmployeeAtriList emp =  outer.new EmployeeAtriList();
		List<EmployeeAttributes> list = emp.getList();
		//dept wise count
		Map<String,Long> deptWiseCount = list.stream()
		.filter(Objects::nonNull)
		.collect(Collectors.groupingBy(n->n.getDepartment(),Collectors.counting()));
		//Employees age >30 working in management
		List<EmployeeAttributes> greaterAndManagement = list.stream()
				.filter(n-> (n.getAge()>30) && (n.getDepartment().equals("Management")))
				.collect(Collectors.toList());
		greaterAndManagement.forEach(n->System.out.print(n+"\n"));
		System.out.println();
		//same as above and adding sorting
		List<EmployeeAttributes> greaterAndManagementAndSorting = list.stream()
				.filter(n-> (n.getAge()>30) && (n.getDepartment().equals("Management")))
				.sorted(Comparator.comparing(EmployeeAttributes::getSalary).reversed())
				.collect(Collectors.toList());
		greaterAndManagementAndSorting.forEach(n->System.out.print(n+"\n"));
		System.out.println();
		//list of only names
		List<String> names = list.stream()
				.filter(n-> (n.getAge()>30) && (n.getDepartment().equals("Management")))
				.sorted(Comparator.comparing(EmployeeAttributes::getSalary).reversed())
				.map(EmployeeAttributes::getName)
				.collect(Collectors.toList());
		names.forEach(n->System.out.print(n+"\n"));
		System.out.println();
		//Grouping by department and cont of emplyees in dept
		Map<String,Long> deptAndCount = list.stream()
				.collect(Collectors.groupingBy(n->n.getDepartment(),Collectors.counting()));
		deptAndCount.forEach((n,v)->System.out.print(n+" has : "+v+"\n"));
		System.out.println();
		//all projects using flatmap
		List<String> projects = list.stream()
				.flatMap(n->n.getProjects().stream())
				.distinct()
				.collect(Collectors.toList());
		System.out.println("All Projects ; "+projects);
		//total salaries given by company
		Optional<Double> salaries = list.stream()
				.map(EmployeeAttributes::getSalary)
				.reduce((a,b)->a+b);
		projects.forEach(n->System.out.print(n+"\n"));
		System.out.println("Salaries : "+salaries);
		
		
		
				
		
	}
}
