
public class isPrime {

	public static void main(String[] args) {
		int count=0;
		for(int i=2;i<=1000;i++) {
			if(checkPrime(i)) {
				count++;
				System.out.println(i);
			}
			//if(count==3) break;
		}
		System.out.println("The total number of Primes are :"+ count);

	}
	public static boolean checkPrime(int num) {
		for(int i=2;i<num;i++) {
			if(num%i==0) {
				return false;
			}
		}
		return true;
	}

}
