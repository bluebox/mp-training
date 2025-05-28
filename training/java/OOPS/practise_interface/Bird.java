package practise_interface;

public class Bird extends Mammel
{


	public void name() {
		System.out.println("name of the bird "+this.getClass().getSimpleName());
		Animals.NAME="giri";

		
	}
	public static void main(String [] args)
	{
		Bird b= new Bird();
		//b.name();
		b.name();
		System.out.println(Animals.NAME);
	}
	 
}