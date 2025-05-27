


public class MethodOverloading {

	
	static double figure(float a,float b) {
		return a*b;
	}
	static double figure(float a) {
		return a*a;
	}
	
	public static void main(String args[]) {
		float a=10.5f,b=5.4f;
		System.out.println(figure(a,b));
		System.out.println(figure(a));
	}
}