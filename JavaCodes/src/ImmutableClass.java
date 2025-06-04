final class Animalss{
	private final String name;
	private final int age;
	public Animalss(String name,int age) {
		this.age=age;
		this.name=name;
	}
	public void run() {
		System.out.println("running");
	}
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
}
//class Dog extends Animal {
//	
//}
//cannot extends the Animal to other classes
public class ImmutableClass {

	public static void main(String[] args) {
		Animalss a=new Animalss("dog",22);
		System.out.println(a.getName());
	}
}
