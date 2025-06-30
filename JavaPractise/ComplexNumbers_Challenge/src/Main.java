
public class Main {

	public static void main(String[] args) {
		ComplexNumbers one = new ComplexNumbers(1,1);
		ComplexNumbers number =new ComplexNumbers(2.5,-1.5);
		one.add(1,1);
		System.out.println(one.getReal());
		System.out.println(one.getImaginary());
		one.subtract(number);
		System.out.println(one.getReal());
		System.out.println(one.getImaginary());
		number.subtract(one);
		System.out.println(number.getReal());
		System.out.println(number.getImaginary());
		
		

	}

}
