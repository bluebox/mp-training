package July17.Practice;

class OuterClass {
	int a = 10;
	static int b = 20;
	private int c = 30;
	private static int d = 40;

	class InnerClass {
		public void print() {
			System.out.println((new OuterClass()).a);
			System.out.println(b);
			System.out.println((new OuterClass()).c); // can access private fields
			System.out.println(d);
		}
	}
}

public class NestedClass {
	public static void main(String[] args) {
		
		OuterClass outerObject = new OuterClass();
		OuterClass.InnerClass innerObject = outerObject.new InnerClass();
		innerObject.print();
	}
}
