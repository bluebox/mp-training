package june26_Loops;

public class Sum3And5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int countOfMatches=0;
		int sumOfMatches=0;
		for(int i=1;i<=1000;i+=1) {
			if (countOfMatches>5)
				break;
			if(i%3==0 && i%5==0) {
				sumOfMatches+=i;
				countOfMatches+=1;
				System.out.println("Found a Match: "+i);
			}
		}
		System.out.println("Sum of the numbers: "+sumOfMatches);

	}

}
