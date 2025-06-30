package Day3_27_06;

public class ComplexNumbers {
	private double real;
	private double imaginary;
	public ComplexNumbers(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	public double getReal() {
		return real;
	}
	public double getImaginary() {
		return imaginary;
	}
	public ComplexNumbers add(double real,double imaginary) {
		return new ComplexNumbers(this.real+real,this.imaginary+imaginary);
	}
	public ComplexNumbers add(ComplexNumbers cp) {
		ComplexNumbers result=new ComplexNumbers(this.real+cp.getReal(),this.imaginary+cp.getImaginary());
		return result;
	}
	public ComplexNumbers subtract(double real,double imaginary) {
		return new ComplexNumbers(this.real-real,this.imaginary-imaginary);
	}
	public ComplexNumbers subtract(ComplexNumbers cp) {
		return new ComplexNumbers(this.real-cp.getReal(),this.imaginary-cp.getImaginary());
	}
	public static void main(String args[]) {
		ComplexNumbers c1 = new ComplexNumbers(1.0, 2.0);
        System.out.println(c1.getReal());

        ComplexNumbers result = c1.add(1.0, 2.0);
        System.out.println(result.real + "+" + result.imaginary + "i");

        result = c1.add(new ComplexNumbers(3.0, 4.0));
        System.out.println(result.real + "+" + result.imaginary + "i");

        result = c1.subtract(0.5, 1.0);
        System.out.println(result.real + "+" + result.imaginary + "i");

        result = c1.subtract(new ComplexNumbers(0.5, 0.5));
        System.out.println(result.real + "+" + result.imaginary + "i");
	}
}
