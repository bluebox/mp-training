
public class ConstructorExample1 {
	
	private String name;
	ConstructorExample1()
	{
		System.out.println("Hello! I am a Constructor");
		name="Sasapu Tarun";
		
	}	
	public static void main(String args[])
	{
	 ConstructorExample1 c=new ConstructorExample1();
	 System.out.println(c.name);
	}

}
