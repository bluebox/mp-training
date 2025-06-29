class Animal{
	public void eat()
	{
		System.out.println("Animal is eating");
	}
}

class Lion extends Animal
{
	public void eat()
	{
		super.eat();
		System.out.println("Lion is Eating ");
	}
	
	public void bark()
	{
		System.out.println("Lion is barking");
	}
}


public class SuperClass1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lion l = new Lion();
		l.eat();
		l.bark();
	}

}
