
public class PrimeNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int counter=0;
		
		for(int i=1;i<=1000;i++) {
			if (isPrime(i)){
				if(counter>2) {
					break;
				}
				counter++;
				System.out.print(i+" ");
			}
		}

	}
	
	public static boolean isPrime(int num) {
		if(num<=1) {
			return false;
		}
		
		for(int i=2;i<=Math.sqrt(num);i++) {
			if(num%i==0) {
				return false;
			}
		}
		return true;
	}

}
