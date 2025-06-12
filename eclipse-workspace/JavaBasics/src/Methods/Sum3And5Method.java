package Methods;

public class Sum3And5Method {
	public static void main(String args[]) {
		int sum = sumRange();
		System.out.println("Sum is "+sum);
	}

	public static int sumRange() {
		int sum = 0,count = 0;
		for (int itr = 1;itr <= 1000;itr++) {
			if ((itr%3 == 0) && (itr%5 == 0)) {
				sum += itr;
				count++;
			}
			if (count == 5) {
				break;
			}
		}
		return sum;
	}
}
