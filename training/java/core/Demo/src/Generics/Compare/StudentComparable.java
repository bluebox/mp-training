package Generics.Compare;

public class StudentComparable  implements Comparable<StudentComparable>{
	
	private String name;
	private int age;
	
	public StudentComparable(String name, int age) {
		this.age=age;
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	
	@Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

  
    
	@Override
	public int compareTo(StudentComparable o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
