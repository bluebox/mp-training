package dev;

public class Math {
	static boolean isEven(int n) {
		return true ?n%2 == 0 :false;
	}
	static boolean isOdd(int n) {
		return !isEven(n);
	}
	static int sqrt(int n) {
		return n*n;
	}
	static boolean is_X_MultipleOf_N(int n,int x) {
		return true ?x%n == 0 :false;
	}
}
