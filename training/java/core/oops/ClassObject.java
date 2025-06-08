package oops;
class Adder{
	public float Add(float a, float b) {
		return a+b;
	}
}
public class ClassObject {

	public static void main(String[] args) {
		Adder ad=new Adder();
		int a=16;
		float b=6;
		System.out.printf("Sum of %d and %f is %f",a,b,ad.Add(a, b));
	}

}
