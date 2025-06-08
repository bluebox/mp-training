package oops;
abstract class Animals{
	abstract void sound();
	private String name;
	public Animals(String name) {
		this.name = name;
	}
	public void hear(String name) {
		System.out.println(name+" hearing");
	}
	public String getName() {
		return name+" ";
	}
}
class Dogs extends Animals{
	public Dogs(String name) {
		super(name);
	}
	public void sound() {
		System.out.println(getName()+ "barks");
	}
}
class Cats extends Animals{
	public Cats(String name) {
		super(name);
	}
	public void sound() {
		System.out.println(getName()+ "meows");
	}
}
class Humans extends Animals{
	public Humans(String name) {
		super(name);
	}
	public void sound() {
		System.out.println(getName()+ "talking");
	}
}
public class Abstraction {
	public static void main(String[] args) {
		Dogs d = new Dogs("Jackie");
		Cats c = new Cats("Jimmy");
		Humans h = new Humans("Indrajeet");
		d.sound();
		c.sound();
		h.sound();
	}
}
