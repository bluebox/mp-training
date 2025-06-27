package Day2_26_06;

public class EvenNumber {
	public static void main(String args[]) {
		int i=5,evenCount=0,oddCount=0;
		while(i<=20) {
			if(isEvenNumber(i)) {
				evenCount++;
				System.out.println(i+" is even");
			}else {
				oddCount++;
			}
			i++;
		}
		System.out.println("Total number of even numbers are "+evenCount);
		System.out.println("Total  number of odd numbers are "+oddCount);
		
	}
	public static boolean isEvenNumber(int n) {
		if(n%2==0) {
			return true;
		}return false;
	}
}
