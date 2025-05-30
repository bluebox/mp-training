package gym;

public abstract class Person {
	public String name;
	public int age;
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public abstract void getDetails();

}
