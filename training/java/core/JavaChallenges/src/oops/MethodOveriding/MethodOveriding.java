package oops.MethodOveriding;

class Arthemetic {
	Integer function(int a, int b) {
		return a + b;
	}
}

public class MethodOveriding {

	public static void main(String[] args) {
		Arthemetic sub = new Arthemetic() {
			Integer function(int a, int b) {
				return Math.abs(a - b);
			}

		};

		System.out.println(sub.function(10, 29));

	}
}