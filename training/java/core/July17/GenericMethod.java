package July17.Practice;

public class GenericMethod {
	public static <T, U> void printDetail(T obj1, U obj2) {
		System.out.println(obj1 + " " + obj2);

	}

	public static <T extends Number> double add(T obj1, T obj2) {
		return obj1.doubleValue() + obj2.doubleValue();
	}
	
	public static void main(String[] args) {

		printDetail("Sahithi", 17);
		printDetail(17, "Sahithi");
		
		System.out.println(add(10,20));
		System.out.println(add(10.5,20.5));
	}
}
