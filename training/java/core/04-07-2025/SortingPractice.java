import java.util.Arrays;
import java.util.*;


class Student implements Comparable<Student>
{
	int id;
	String name;
	int age;
	public Student(int id, String name, int age) {

		this.id = id;
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	@Override
	public int compareTo(Student anotherStudent) {
//	
//		int x=this.name;
//		int y=anotherStudent.name;
//		return (x>y)?-1:((x==y)?0:1);
//		return x-y;
//		return Integer.compare(y, x);
		return this.name.compareTo( anotherStudent.name);
	}
	
}
public class SortingPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Comparator<Integer> comp=(Integer o1, Integer o2)->Integer.compare(o1, o2);
					
			
							
//		Integer[] arr= {2,6,3,2,1};
//		Arrays.sort(arr,comp);
//		System.out.println(Arrays.toString(arr));
		
		
		Comparator<Student> comp=new Comparator<Student>()
				{

					@Override
					public int compare(Student s1, Student s2) {
						return s1.name.compareTo(s2.name);
						
					}
			
				};
		List<Student> l=Arrays.asList(
				new Student(3,"tarun",34),
				new Student(1,"sai",45),
				new Student(2,"ravi",33)
				); 
		Collections.sort(l,comp);
		System.out.println(l); 
		


	}
}


