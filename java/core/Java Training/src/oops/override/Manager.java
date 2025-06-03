package oops.override;

public class Manager {
	
	protected String name,joining;
	protected int age;
	
	public Manager(String name,String joining,int age) {
		this.name=name;
		this.joining=joining;
		this.age=age;
	}
	public void display() {
		System.out.println("Manager Details :");
		System.out.println("Name : "+name);
		System.out.println("Joined on : "+joining);
		System.out.println("Age : "+age);
	}
}
