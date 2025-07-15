package practice;

import java.util.function.Predicate;

public class Ex4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Predicate<Integer> p=i->i<18;
//		System.out.println(p.test(10));

		
		Predicate<String> ismatches=i->(i=="Hello");
		System.out.println(ismatches.test("ello"));
	}

}
