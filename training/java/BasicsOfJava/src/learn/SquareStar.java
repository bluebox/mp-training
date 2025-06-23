package learn;

public class SquareStar {
	public static void main(String[] args) {
		
		int num = 20;
		printSquareStar(num);
	}
	
	public static void printSquareStar(int number) {
		
		if(number < 5) {
			
			System.out.println("invalid value");
			return;
		}
		for(int i = 0;i < number;i++) {
			
			for(int j = 0;j < number;j++) {
				
				if((i == j) || (i + j == number-1) || (i == 0) || (j == 0) || (i==number-1) || (j==number-1)){
					
					System.out.print('*');
				}
				else {
					System.out.print(' ');
				}
			}
			System.out.println();
		}
	}
}
