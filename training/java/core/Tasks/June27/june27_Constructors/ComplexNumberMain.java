package corejava.june27_Constructors;

public class ComplexNumberMain {

	public static void main(String[] args) {
		ComplexNumber one=new ComplexNumber(1.0,1.0);
		ComplexNumber number=new ComplexNumber(2.5,-1.5);
		one.add(1,1);
		System.out.println("one.real= "+one.getReal());
		System.out.println("one.imaginary= "+one.getImg());
		one.subtract(number);
		System.out.println("one real= "+one.getReal());
		System.out.println("one.imaginary= "+one.getImg());
		number.subtract(one);
		System.out.println("number.real= "+number.getReal());
		System.out.println("number.imaginary= "+number.getImg());
	}

}
