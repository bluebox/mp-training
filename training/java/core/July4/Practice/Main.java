package July4.Practice;

class MyClass {
	static int staticVar;
	int instanceVar;

    // Static block 1
	static {
		staticVar = 10;
		System.out.println("Static block 1 executed. staticVar = " + staticVar);
	}
	// Static block 2
	static {
		staticVar = 20;
		System.out.println("Static block 2 executed. staticVar = " + staticVar);
	}
	
	// Non-static block 1
	{
		instanceVar = 100;
		System.out.println("Non-static block 1 executed. instanceVar = " + instanceVar);
	}
	// Non-static block 2
	{
		instanceVar = 200;
		System.out.println("Non-static block 2 executed. instanceVar = " + instanceVar);
	}

	// Constructor
	MyClass() {
		System.out.println("Constructor executed");
	}
}

public class Main {
	public static void main(String[] args) {
		System.out.println("Main method started");
		MyClass obj = new MyClass();
		System.out.println(obj);
	}
}
