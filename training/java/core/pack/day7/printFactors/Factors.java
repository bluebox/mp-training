package day7.printFactors;

public class Factors {
	public static void printFacts(int t1) {
		if(t1<1) {
			System.out.println("invalid value");
			return;
		}
		for(int i=1;i<t1/2;i++) {
			if(t1%i==0)
				System.out.print(i+" ");
		}
	}
}
