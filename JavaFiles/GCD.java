
public class GCD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(gcd(25, 15)); 
		System.out.println(gcd(12, 30)); 
		System.out.println(gcd(9, 18));  
		System.out.println(gcd(81, 153));


	}
	
	public static int gcd(int first, int second) {
        if (first < 10 || second < 10) {
            return -1;
        }

        int min = Math.min(first, second);
        int gcd = 1;

        for (int i = min; i >= 1; i--) {
            if (first % i == 0 && second % i == 0) {
                gcd = i;
                break;
            }
        }

        return gcd;
    }
}
