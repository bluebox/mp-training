package July17.Practice;

class test<T> {
	T obj;

	test(T obj) {
		this.obj = obj;
	}

	public T getObj() {
		return obj;
	}
}

public class GenericExample {
	public static void main(String[] args) {
		
		test<Integer> example1 = new test<Integer>(17);
		System.out.println(example1.getObj());
		
		test<String> example2 = new test<String>("Sahithi");
		System.out.println(example2.getObj());

//		example1 = example2; gives error
	}
}
