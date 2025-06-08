package oops;
class Animal{
	public void move(String name) {
		System.out.printf("%s moving %n",name);
	}
	public void sound(String name) {
		System.out.printf("%s making noise %n",name);
	}
}
class Dog extends Animal{
}
class Human extends Animal{
	@Override
	public void sound(String name) {
		System.out.printf("%s talking %n", name);
	}
}
public class Polymorphism {
	public static void main(String[] args) {
		
		//overriding
		Dog dog = new Dog();
		dog.move("Jackie");
		dog.sound("Jackie");
		Human ram = new Human();
		ram.move("Ramu");
		ram.sound("Ramu");
		
		//overloading
		Overload o = new Overload();
		o.name("Shiva","Ram");
		o.name("Arjun");
	}
}
class Overload{
	String name;
	public void name(String firstName, String lastName) {
		name=firstName+" "+lastName;
		call();
	}
	public void name(String name) {
		this.name=name;
		call();
	}
	public void call() {
		Human person = new Human();
		person.sound(name);
	}
}