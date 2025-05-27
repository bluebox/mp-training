package Main;

public class Relational {
	public static void main(String args[]) {
		
		int a = 10, b = 5;
		
		System.out.println("a == b "+(a == b));
		System.out.println("a > b "+(a > b));
		System.out.println("a < b "+(a < b));
		System.out.println("a >= b "+(a >= b));
		System.out.println("a <= b "+(a <= b));
		System.out.println("a != b "+(a != b));
		
		String str1 = "Hello", str2 = "Hello";
		String str3 = new String("Hello");
		System.out.println("str1 == str3 "+(str1 == str3));
		System.out.println("str1.equals(str3) "+(str1.equals(str3)));
		System.out.println("str1 == str2 "+(str1 == str2));
	}
}
