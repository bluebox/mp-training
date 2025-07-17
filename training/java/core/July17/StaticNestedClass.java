package July17.Practice;

class outerClass {
	int a = 10;
	static int b = 20;
	private int c = 30;
	private static int d = 40;

	static class innerClass {
		public void print() {
			System.out.println((new outerClass()).a);
			System.out.println(b);
			System.out.println((new outerClass()).c); // can access private fields
			System.out.println(d);
		}
	}
}

public class StaticNestedClass {
	public static void main(String[] args) {
		outerClass.innerClass test = new outerClass.innerClass();
		test.print();
	}
}
