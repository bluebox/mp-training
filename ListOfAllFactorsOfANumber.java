
public class ListOfAllFactorsOfANumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printFactors(6);
		printFactors(32);
		printFactors(0);
		printFactors(-1);
		printFactors(16);
		printFactors(-9);

	}

	
	public static void printFactors(int n) {
		if(n<1) {
			System.out.println("Invalid Value");
		}
		else
		{
			for(int i=1;i<=n;i++) {
				if(n%i == 0) {
					System.out.print(i+" ");
				}
			}
			System.out.print("\n");
		}
	}
}
