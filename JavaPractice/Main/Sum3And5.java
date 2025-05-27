package Main;


public class Sum3And5 {
	public static void main(String args[]) {
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
		System.out.println("Sum is "+sum);
	}
}
