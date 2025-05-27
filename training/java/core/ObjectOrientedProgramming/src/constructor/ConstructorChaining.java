package constructor;

class Person
{
	private String name;
	private int age;
	private String gender;
	private boolean isAlive;
	public Person(String name, int age, String gender, boolean isAlive) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.isAlive = isAlive;
	}
	public Person(String name, int age, boolean isAlive)
	{
		this(name,age,null,isAlive);
	}
	public Person(String name, int age)
	{
		this(name,age,true);
	}
	public Person(String name)
	{
		this(name,0);
	}
	public Person()
	{
		this(null);
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", isAlive=" + isAlive + "]";
	}
	
}

public class ConstructorChaining {
	public static void main(String[] args) {
		Person p = new Person();
		System.out.println(p);
		p = new Person("madhav");
		System.out.println(p);
		p = new Person("madhav",21);
		System.out.println(p);
		p = new Person("madhav",21,true);
		System.out.println(p);
		p = new Person("madhav",21,"Male",true);
		
				
	}
}
