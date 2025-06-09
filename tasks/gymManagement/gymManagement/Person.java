package gymManagement;

public abstract class Person {
	private String name;
	private int age;
	public abstract void showDetails();
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	
}
