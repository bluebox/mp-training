class Student{
	private String name;
	private int age;
	public Student(String name) {
		this(name,0);
	}
	public Student(String name,int age) {
		this.age=age;
		this.name=name;
	}
	// below is a example of constructer override 
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
}
public class ConstructerOverloading {

	public static void main(String[] args) {
		 Student a=new Student("anand");
		 Student b=new Student("Abhi",22);
		 // using getter functions because attributes are private
		 System.out.println(a.getAge()+" "+a.getName());
		 System.out.println(b.getAge()+" "+b.getName());
	}

}
