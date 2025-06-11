package Generics.GenericClasses;

public class Main {
	
	public static void main(String[] args) {
		//old non-generic 
		OldBox old = new OldBox();
		old.set("hello");
		String s=(String)old.get();
		old.set(123);
		System.out.println(s);
		
		
		//generic
		
		Box<String> box= new Box<>();
		box.set("hi");
		//box.set(123);
		String message= box.get();
		System.out.println(message);
		
		Box<Integer> box1= new Box<>();
		box1.set(23);
		Integer num= box1.get();
		System.out.println(num);

	}

}
