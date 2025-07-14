package regxmini;
import java.util.*;


public class mini {
	public static void main(String[] args) {
		String sentence="Hello world";
		boolean matchornot=sentence.matches("Hello world");
		System.out.println(matchornot);
		String ch1="[A-Z].*\\.";
		for(String s:List.of("The bike is red.",
				"I am a new student.",
				"hello World.",
				"How are you?")) {
			System.out.println(s.matches(ch1));
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		String ch2="[A-Z].+[.?!]";
			for(String k:List.of("The bike is red, and has flat tires",
					"I love being a new L.P.A student!",
					"Hello, friends and family: Welcome!")) {
				System.out.println(k.matches(ch2));
			}
		}
	}


