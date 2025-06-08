package oops;
class Calc{
	public float add(float a, float b) {
		return a+b;
	}
	public float sub(float a, float b) {
		return a-b;
	}
}
class AdvCalc extends Calc{
	public float mul(float a, float b) {
		return a*b;
	}
	public float div(float a, float b) {
		return a/b;
	}
}
class VeryAdvCalc extends AdvCalc{
	public double power(float a, float b) {
		return Math.pow(a, b);
	}
}
public class Inheritance {

	public static void main(String[] args) {
		VeryAdvCalc calci = new VeryAdvCalc();
		System.out.printf("Sum of %f and %f is %f %n", (float) 16, (float) 6, calci.add(16,6));
		System.out.printf("Subtracting %f from %f is %f %n", (float) 23, (float) 45, calci.sub(45,23));
		System.out.printf("Product of %f and %f is %f %n", (float) 11, (float) 2, calci.mul(11,2));
		System.out.printf("Dividing %f by %f is %f %n", (float) 88, (float) 4, calci.div(88,4));
		System.out.printf("%f to the power of %f is %f %n", (float) 22, (float) 2, calci.power(22,2));
	}

}
