package July17.Practice;

class test2<T, U> {
	T obj1;
	U obj2;

	test2(T obj1, U obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;
	}

	void printDetails() {
		System.out.println(obj1 + " " + obj2);
	}
}

public class GenericClass2 {
	public static void main(String[] args) {
		
		test2<String, Integer> ex1 = new test2<String, Integer>("Sahithi", 17);
		ex1.printDetails();
		
		test2<Integer, String> ex2 = new test2<Integer, String>(17, "Sahithi");
		ex2.printDetails();
	}
}
