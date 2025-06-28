
public class Strings {
	
	public static void main(String[] args) {
		String s ="This a String";
		String s1=s ,s2="This";
		
		System.out.println(s);
		System.out.println(s.length());
		System.out.println(s.charAt(0));
		System.out.println(s.compareTo(s1));
		System.out.println(s.contains(s2));
		System.out.println(s.endsWith("String"));
		System.out.println(s.indexOf('a'));
		
		int a=20;
		
		String b="30.05";
		
		String c = a+b;
		
		System.out.println(c);
	}

}
