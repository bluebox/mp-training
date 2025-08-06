
public class StarPattern {

	public static void main(String[] args) {
		
		printSquareStar(8);
        printSquareStar(4); 
	}
	
	public static void printSquareStar(int number) {
		
		if( number < 5)
		{
			System.out.println("Invalid Value");
			return;
		}
		
		for(int i=1; i<=number; i++) {
			for(int j=1; j<=number; j++) {
				
				if(i==1 || i==number || i==j || j==1 || j==number || j==number-i+1) {
				System.out.print("*");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
	}
}
