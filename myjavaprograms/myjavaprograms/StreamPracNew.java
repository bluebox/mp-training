package myjavaprograms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;

class Employee{
	
	String name , id , dept ;
	int age;
	double salary;
	
	
	
	Employee(String name , String id , int age , String dept ,double salary ){
		this.name = name ;
		this.id = id;
		this.age = age;
		this.dept = dept;
		this.salary = salary;
	}
	
	public String toString() {
		return "name : %s , age : %d , id : %s , dept : %s , salary : %f".formatted(this.name , this.age , this.id , this.dept , this.salary);
	}
}

public class StreamPracNew {
	public static void main(String args[]) {
		
		Employee emp1 = new Employee("naga" , "1001" , 24 , "cse" , 40000);
		Employee emp2 = new Employee("reddy" , "1004" , 34 , "cse" , 60000);
		Employee emp3 = new Employee("muni" , "1005" , 22 , "cst" , 20000);
		Employee emp4 = new Employee("sagar" , "1008" , 40 , "ece" , 42000);
		Employee emp5 = new Employee("suraj" , "1006" , 32 , "cst" , 45000);
		Employee emp6 = new Employee("kaif" , "1011" , 30, "cse" , 50000);
		Employee emp7 = new Employee("mahi" , "1007" , 24 , "csc" , 32000);
		Employee emp8 = new Employee("tiger" , "1009" , 28 , "ece" , 48000);
		Employee emp9 = new Employee("nikhil" , "1002" , 20 , "csc" , 40500);
		Employee emp10 = new Employee("kush" , "1003" , 29 , "eee" , 45000);
		Employee emp11 = new Employee("mani" , "1010" , 25 , "eee" , 30000);
		
		List<Employee> list = Arrays.asList(emp1 , emp2 , emp3 , emp4 , emp5 , emp6, emp7 , emp8 ,emp9 , emp10 , emp11);
		
		list.forEach(emp -> System.out.println(emp));
		
		System.out.println("------------------------------------------------------------------------------");
		
		
		//find no. of depts
		
		long deptCount = list.stream()
				.map(emp -> emp.dept)
				.distinct()
				.count();
		
		System.out.println("dept count : " + deptCount);
		
		//show all the depts 
		
		list.stream().map(emp -> emp.dept).distinct().forEach(System.out::println);
		System.out.println("-------------------------------------------");
		
		//show depts in descending order 
		
		list.stream().map(emp -> emp.dept).distinct().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		System.out.println("-------------------------------------------");
		
		//find max salary
		Optional<Employee> emp = list.stream().collect(Collectors.maxBy(Comparator.comparingDouble(e -> e.salary)));
		
		System.out.println("max salary : " + emp.get().salary);
		System.out.println("-------------------------------------------");
		
		//find min salary
		
		Optional<Employee> empa = list.stream().min(Comparator.comparing(e -> e.salary));
		
		System.out.println("min salary : " + empa.get().salary);
		
		System.out.println("-------------------------------------------");
		
		//display all employees names
		System.out.println("employees list : ");
		list.stream().map(e->e.name).forEach(System.out::println);
		
		System.out.println("-------------------------------------------");
		
		//find emp by their id
		List<Employee> emps = list.stream().filter(e -> e.id.equals( "1001")).collect(Collectors.toList());
		System.out.println("emp with id 1001 : ");
		emps.forEach(System.out::println);
		
		
		//find max age 
		int maxAge = list.stream().max(Comparator.comparing(e -> e.age)).get().age;
		System.out.println("employee with max age : " + maxAge);
		
		
		//find total salary 
		double totalSalary = list.stream().mapToDouble(e -> e.salary).sum();
		System.out.println("total salary : "+totalSalary);
		
		//find average salary
		
		double averageSalary = list.stream().collect(Collectors.averagingDouble(e->e.salary));
		System.out.println("average salary : " + averageSalary);
		
		//find average age 
		
		double averageAge = list.stream().collect(Collectors.averagingDouble(e->e.age));
		System.out.println("average age : " + averageAge);
		
		//find highest salary by dept wise
		
		list.stream().collect(Collectors.groupingBy(emp -> emp.dept , Collectors.maxBy(Comparator.comparing(e -> e.salary)))));		
		//find minimum salary by dept wise
		
		//find total salary dept wise
		
		//find the employees having salary greater than 40000
		
		//sort the employees by their age 
		
		//sort the employees by their name 
		
		//find total number of employees 
		
		//find the number of people in each department
		
		//merge all employees with delimitter , 
		
		//find sum of salaries of employees having even no as age.
		
		
		
		
		
		
		
	}
}
