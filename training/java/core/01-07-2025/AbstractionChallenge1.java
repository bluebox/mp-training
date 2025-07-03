abstract class Animal
{
	public void print()
	{
		System.out.println("I am an animal");
	}
	
	abstract void dog();
}
class Dog extends Animal
{
	
	public void dog()
	{
		System.out.println("I am a dog..!");
	}
}
public class AbstractionChallenge1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog d1= new Dog();
		d1.print();
		d1.dog();

	}

}
