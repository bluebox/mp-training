package concepts;

import java.util.Arrays;

public class Strings {

	public static void main(String[] args) {
		String s1="Bheema";
		String s2="Bheema";
		String s3=new String("Bheema");
		String s4=new String("Bheema");
		String s5=s3;
		System.out.println(s1==s2);
		System.out.println(s1==s3);
		System.out.println(s3==s5);
		System.out.println(s3==s4);
		
		System.out.println(s3.equals(s4));
		System.out.println(s3.equals(s5));
		System.out.println(s1.equals(s2));
		
		System.out.println(s1.length());
		System.out.println(s3.length());
		
		System.out.println(s3.charAt(4));
		
		System.out.println(s3.substring(0,3));
		System.out.println(s3.substring(3));
		
		s5= "Hello ".concat(s3);
		System.out.println(s5);
		
		System.out.println(s5.indexOf(s4));
		System.out.println(s5.lastIndexOf('a'));
		
		System.out.println(s5.toLowerCase());
		System.out.println(s5.toUpperCase());
		
		s4="ba".toUpperCase()+"na".repeat(2).toUpperCase();
		System.out.println(s4);
		
		System.out.println(s5.replace('e','i'));
		s2="apple mango apple banana apple";
		System.out.println(s2.replace("apple","orange"));
		System.out.println(s2.replaceAll("apple","orange"));
		System.out.println(s2.replaceFirst("apple","orange"));
		
		String[] fruits= s2.split(" ");
		System.out.println(Arrays.toString(fruits));
		
		System.out.println(s5.startsWith("H"));
		System.out.println(s5.endsWith("a"));
		
		System.out.println(s2.contains("banana"));
		s4="";
		System.out.println(s4.isEmpty());
		System.out.println(s1.toCharArray());
		
		int x=22;
		s4=String.valueOf(x);
		System.out.println(s4);
	}

}
