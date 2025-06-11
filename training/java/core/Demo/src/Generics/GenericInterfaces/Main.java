package Generics.GenericInterfaces;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyGenericContainer<Double> doubleContainer = new MyGenericContainer<>();
        doubleContainer.put(3.14);
        System.out.println("Double in container: " + doubleContainer.get());

        MyGenericContainer<Boolean> booleanContainer = new MyGenericContainer<>();
        booleanContainer.put(true);
        System.out.println("Boolean in container: " + booleanContainer.get());

	}

}
