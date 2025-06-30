package corejava.june26_loops;

public class WhileLoopChallenge {

	public static void main(String[] args) {
		int n=5;
		int evenCount=0;
		
		//To display 5 even numbers
		while (n<=20) {
			if(isEvenNumber(n)) {
				evenCount++;
				System.out.println(n);
				if (evenCount==5)
					break;
			}
			n++;
		}
		n++;
		
		//To display total number of even and odd numbers in given range
		while(n<=20) {
			evenCount++;
			n+=2;
		}
		System.out.println("There are "+evenCount+" even numbers & "+((20-4)-evenCount)+" odd numbers found in between \"5\" and \"20\"");
		
	}
	
	
	public static boolean isEvenNumber(int number) {
		return (number&1)==0;
	}

}
