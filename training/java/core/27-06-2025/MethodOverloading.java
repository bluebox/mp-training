
public class MethodOverloading {
	public static int add(int num1 ,int num2)
	{
		return num1+num2;
	}
	
	public static double add(int num1,int num2,double num3)
	{
		return num1+num2+num3;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	System.out.println(add(4,5));
	System.out.println(add(4,5,6.8));

	}

}
