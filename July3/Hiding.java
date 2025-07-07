package July3;

//Hiding vs Overriding

class Complex {
	public static void f1() {
		System.out.println("f1 method of the Complex class is executed.");
	}

	public void f2() {
		System.out.println("f2 method of the Complex class is executed.");
	}
}

class Sample extends Complex {
	public static void f1() {
		System.out.println("f1 method of the Sample class is executed.");
	}

	public void f2() {
		System.out.println("f2 method of the Sample class is executed.");
	}
}

public class Hiding {
	public static void main(String args[]) {
		Complex d1 = new Complex();
		Complex d2 = new Sample();
		Sample d3 = new Sample();
		
		d1.f1();
		d2.f1();
		d3.f1();
		
		d1.f2();
		d2.f2();
		d3.f2();
	}
}
