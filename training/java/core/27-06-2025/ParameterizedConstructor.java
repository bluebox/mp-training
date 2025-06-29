
public class ParameterizedConstructor {

	String names;
	ParameterizedConstructor(String str)
	{
		names=str;
		System.out.println("Name Is: "+ names);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParameterizedConstructor p=new ParameterizedConstructor("tarun");
		ParameterizedConstructor p1=new ParameterizedConstructor("Raju");
		ParameterizedConstructor p2=new ParameterizedConstructor("Range");
		ParameterizedConstructor p3=new ParameterizedConstructor("Ramu");

	}

}
