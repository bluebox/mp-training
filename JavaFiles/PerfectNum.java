
public class PerfectNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPerfectNumber(6));
		System.out.println(isPerfectNumber(5));
		System.out.println(isPerfectNumber(-1));


	}

	    public static boolean isPerfectNumber(int number) {
	        if (number < 1) {
	            return false;
	        }

	        int sum = 0;

	        for (int i = 1; i < number; i++) {
	            if (number % i == 0) {
	                sum += i;
	            }
	        }

	        return sum == number;
	    }
}
