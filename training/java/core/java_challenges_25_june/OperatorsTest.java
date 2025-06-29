package training.java.core.java_challenges_25_june;

public class OperatorsTest {
	public static void main(String[] args) {
		
		//notes: the Arithmetic operators are : + , - , / , * , %
		System.out.println("1 + 2 = " + (1 + 2));
		System.out.println("4 - 2 = " + (4 - 2));
		System.out.println("6 / 3 = " + (6 / 3));
		System.out.println("5 * 19 = " + (5 * 19));
		System.out.println("10 % 3 (the remainder of 10/3) = " + (10 % 3));

		// Assignment operators : = , += , -= , *= , /= , %= , &= , |=
		int a = 5;
		System.out.println("\nAssignment Operators:");
		a += 3;
		System.out.println("a += 3 -> " + a);
		a -= 2;
		System.out.println("a -= 2 -> " + a);
		a *= 4;
		System.out.println("a *= 4 -> " + a);
		a /= 3;
		System.out.println("a /= 3 -> " + a);
		a %= 5;
		System.out.println("a %= 5 -> " + a);
		a &= 2;
		System.out.println("a &= 2 -> " + a);
		a |= 1;
		System.out.println("a |= 1 -> " + a);

		// Relational operators : == , != , > , < , >= , <=
		System.out.println("\nRelational Operators:");
		System.out.println("5 == 5 -> " + (5 == 5));
		System.out.println("5 != 3 -> " + (5 != 3));
		System.out.println("7 > 3 -> " + (7 > 3));
		System.out.println("2 < 5 -> " + (2 < 5));
		System.out.println("6 >= 6 -> " + (6 >= 6));
		System.out.println("4 <= 8 -> " + (4 <= 8));

		// Logical operators : && , || , !
		System.out.println("\nLogical Operators:");
		System.out.println("(5 > 3) && (8 > 5) -> " + ((5 > 3) && (8 > 5)));
		System.out.println("(5 < 3) || (8 > 5) -> " + ((5 < 3) || (8 > 5)));
		System.out.println("!(5 == 5) -> " + (!(5 == 5)));

		// Bitwise operators : & , | , ^ , ~ , << , >> , >>>
		System.out.println("\nBitwise Operators:");
		System.out.println("5 & 3 -> " + (5 & 3));    // AND
		System.out.println("5 | 3 -> " + (5 | 3));    // OR
		System.out.println("5 ^ 3 -> " + (5 ^ 3));    // XOR
		System.out.println("~5 -> " + (~5));          // NOT
		System.out.println("5 << 1 -> " + (5 << 1));  // Left shift
		System.out.println("5 >> 1 -> " + (5 >> 1));  // Right shift
		System.out.println("5 >>> 1 -> " + (5 >>> 1));// Unsigned right shift
	}
}