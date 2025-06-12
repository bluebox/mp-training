package Generics.Compare;

public class Student {
	
	private String name;
	private int id;
	private int age;
	
	public Student(String name, int age, int id) {
		this.age=age;
		this.name=name;
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public int getAge() {
		return age;
	}
	
	public String toString() {
		return "Student{" +"name= "+name+
				" age= "+age+" id= "+id+"}";  
	}

}
