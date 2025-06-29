class Lion1
{
	String animal="Lion";
}

class Dog extends Lion1
{
	String animal="dog";
	public void printType()
	{
		System.out.println("This is "+animal);
		System.out.println("This is "+ super.animal);	
	}
}


public class SuperClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog d= new Dog();
		d.printType();

	}

}
