package Interface;

public class Interface {

	public static void main(String[] args) {
		Dog dog=new Dog();
		dog.run();
		// we cannot initiate the interface objects because they are static and final
		Human.fun();
		MultipleInterface a=new MultipleInterface();
		a.drive();
		a.jump();
		a.run();
		// we can use interface by using the reference of a class that implements interface
		Animal b=new Dog();
		b.run();
	}

}
