package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Employee implements Comparable<Employee>
{

	String name;
	int id;
	int age;
	
	
	public Employee(String name, int id, int age) {
		this.name = name;
		this.id = id;
		this.age = age;
	}


	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", age=" + age + "]";
	}


	@Override
	public int compareTo(Employee o) {
		 
		return this.id-o.id;
//		return this.name.compareTo(o.name);
//		return o.age-this.age;
	
	}
	
}
public class Ex5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> list= Arrays.asList(1,2,3,9,8,7,6,5,4);
		Comparator<Employee> comp=new Comparator<Employee>()
				{

					
					@Override
					public int compare(Employee o1, Employee o2) {
						// TODO Auto-generated method stub
						return o1.age-o2.age;
					}
			
				};
				
				
		System.out.println(list);
		
		
		List<Employee> l1=Arrays.asList(
				new Employee("Tarun",1,23),
				new Employee("ravi",3,33),
				new Employee("Hari",2,45)
				);
		Collections.sort(l1);
		System.out.println(l1);

		Collections.sort(l1,comp);

	}

}
